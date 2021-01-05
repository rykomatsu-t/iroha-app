package com.solxyz.irohaapp.block;

import java.security.KeyPair;

import com.solxyz.irohaapp.entity.UserInfo;
import com.solxyz.irohaapp.utility.IrohaConnection;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import jp.co.soramitsu.iroha.java.Transaction;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SendAsset {

    /**
     * adminユーザーのアカウント
     */
    private final String ADMIN = "admin@test";

    /**
     * testユーザーのアカウント
     */
    private final String TEST = "test@test";

    /**
     * testドメインに存在するcoinアセット
     */
    private final String COIN = "coin#test";

    public void send(UserInfo from, UserInfo to, int quantity) throws Exception {

        // iroha接続用のクラスを生成
        IrohaConnection irohaUtil = new IrohaConnection("localhost", 50051);

        // ラッパークラスで生成されたiroha用APIを取得
        IrohaAPI api = irohaUtil.getApi();

        // 署名用のKeyPairを作成
        KeyPair keyPair = irohaUtil.createExistKeyPair(from.getPub(), from.getPriv());

        // トランザクションの組み立て
        // 内容：admin@test から test@test へ coin#test を 20.0 送信する
        val tx = Transaction.builder(from.getName()).transferAsset(from.getName(), to.getName(), COIN, "coinの転送", String.valueOf((double) quantity)).sign(keyPair).build();

        // 組み立てたトランザクションを実行
        api.transaction(tx).blockingFirst();
    }


}
