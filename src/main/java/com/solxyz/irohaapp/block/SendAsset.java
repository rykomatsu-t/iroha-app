package com.solxyz.irohaapp.block;

import java.security.KeyPair;

import com.solxyz.irohaapp.utility.IrohaConnection;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import jp.co.soramitsu.iroha.java.Transaction;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SendAsset {

    /**
     * adminの公開鍵
     */
    private final String ADMIN_PUB = "313a07e6384776ed95447710d15e59148473ccfc052a681317a72a69f2a49910";

    /**
     * adminの秘密鍵
     */
    private final String ADMIN_PRIV = "f101537e319568c765b2cc89698325604991dca57b9716b58016b253506cab70";

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

    public void send(String from, String to, double quantity) throws Exception {

        // iroha接続用のクラスを生成
        IrohaConnection irohaUtil = new IrohaConnection("localhost", 50051);

        // ラッパークラスで生成されたiroha用APIを取得
        IrohaAPI api = irohaUtil.getApi();

        // 署名用のKeyPairを作成
        KeyPair keyPair = irohaUtil.createExistKeyPair(ADMIN_PUB, ADMIN_PRIV);

        // トランザクションの組み立て
        // 内容：admin@test から test@test へ coin#test を 20.0 送信する
        val tx = Transaction.builder(ADMIN).transferAsset(ADMIN, TEST, COIN, "coinの転送", "20.0").sign(keyPair).build();

        // 組み立てたトランザクションを実行
        api.transaction(tx).blockingFirst();
    }


}
