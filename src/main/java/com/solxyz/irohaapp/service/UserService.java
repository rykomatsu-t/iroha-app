package com.solxyz.irohaapp.service;

import com.solxyz.irohaapp.block.SendAsset;
import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ユーザーを検索するサービス
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    SendAsset sendAsset;


    /**
     * 入力したname と pass をもとにログイン判定
     * @param name ユーザー名
     * @param pass パスワード
     * @return
     */
    public boolean login(String name, String pass){
        UserInfo user = repository.findByName(name);
        if(user.getPassword() == pass){
            return true;
        } else {
            return false;
        }
    }

    /**
     * ユーザー名からユーザーの情報を取得
     * @param name
     * @return
     */
    public UserInfo searchUserByName(String name) {
        return repository.findByName(name);
    }

    /**
     * ユーザー追加
     * h2DBを使っているため、ユーザー登録のため仮実装
     * 本番ではpostgresを使うためここは不要
     * @return
     */
    public String addUser() {
        UserInfo admin = new UserInfo();
        admin.setId(1);
        admin.setName("admin");
        admin.setPassword("pass");
        admin.setQuantity(100);
        repository.saveAndFlush(admin);

        UserInfo user = new UserInfo();
        user.setId(2);
        user.setName("user");
        user.setPassword("pass");
        user.setQuantity(0.0);
        repository.saveAndFlush(user);

        return "ok";
    }

}
