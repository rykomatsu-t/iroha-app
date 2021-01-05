package com.solxyz.irohaapp.service;

import com.google.type.Money;
import com.solxyz.irohaapp.entity.History;
import com.solxyz.irohaapp.repository.AssetRepository;
import com.solxyz.irohaapp.repository.HistoryRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solxyz.irohaapp.block.SendAsset;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ユーザーを検索するサービス
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    SendAsset sendAsset;


    /**
     * 入力したname と pass をもとにログイン判定
     *
     * @param name ユーザー名
     * @param pass パスワード
     * @return
     */
    public boolean login(String name, String pass) {
        UserInfo user = userRepository.findByName(name);
        if (user != null && user.getPassword().equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ユーザー名からユーザーの情報を取得
     *
     * @param name
     * @return
     */
    public UserInfo getMyInfo(String name) {
        return userRepository.findByName(name);
    }

    /**
     * ユーザー一覧を取得
     *
     * @return ユーザーリスト
     */
    public List<UserInfo> getUserList() {
        return userRepository.findAll();
    }

    public List<UserInfo> searchUser(int dep, String name) {
        if (dep > 0 && !StringUtil.isNullOrEmpty(name)) {
            return userRepository.findByDepIdAndName(dep, name);
        }

        if (dep <= 0) {
            List<UserInfo> userList = new ArrayList<>();
            userList.add(userRepository.findByName(name));
            return userList;
        }

        if (StringUtil.isNullOrEmpty(name)) {
            return userRepository.findByDepId(dep);
        }

        return new ArrayList<UserInfo>();
    }


    /**
     * コインの送信を実行する
     * トランザクションに書き込みを行い、DBを更新する
     */
    public boolean send(String fromStr, String toStr, int quantity) throws Exception {
        UserInfo from = userRepository.findByName(fromStr);
        UserInfo to = userRepository.findByName(toStr);

        // 送り先か送り元が入力されていない場合失敗
        if (fromStr == null || "".equals(fromStr) || toStr == null || "".equals(toStr)) {
            return false;
        }

        // 送り先と送り元が同一なら失敗
        if (fromStr.equals(toStr)) {
            return false;
        }

        // 送り先か送り主のどちらかが存在しないユーザーなら失敗
        if (from == null || to == null) {
            return false;
        }

        // 送るアセットのquantityが0以下なら失敗
        if (quantity <= 0) {
            return false;
        }

        // 自分の持つアセットより大きい数字を送ろうとすると失敗
        if (from.getQuantity() - quantity < 0) {
            return false;
        }

        // 送る側、受け取る側、いくら渡すかの情報を入力し、ブロックに書き込む
        sendAsset.send(from, to, quantity);

        // 送り元からアセットを減算
        from.setQuantity(from.getQuantity() - quantity);

        // 送り先にアセットを加算
        to.setQuantity(to.getQuantity() + quantity);

        // DBの更新
        // TODO　トランザクション処理が必要
        userRepository.save(from);
        userRepository.save(to);

        History history = new History();
        history.setAssetId(assetRepository.findAll().get(0));
        history.setMessage(""); // TODO メッセージを入れる
        history.setSendId(from);
        history.setRecieveId(to);
        history.setVal((double)quantity);
        history.setSendTime(new Date());

        historyRepository.save(history);

        return true;
    }

}
