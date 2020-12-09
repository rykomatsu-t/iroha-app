package com.solxyz.irohaapp.utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyPair;

import org.testcontainers.shaded.org.bouncycastle.util.encoders.Hex;

import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3;
import jp.co.soramitsu.iroha.java.IrohaAPI;
import lombok.Getter;

/**
 * irohaプロセスとの接続を担うラッパークラス
 * @author RYKOMATSU
 *
 */
public class IrohaConnection {

    public IrohaConnection(String host, int port) throws URISyntaxException {
        this.uri = new URI("grpc", "", host, port, "", "", "");
        this.api = new IrohaAPI(uri);
    }

    @Getter
    private URI uri;

    @Getter
    private IrohaAPI api;

    /**
     * 渡された鍵をもとにKeyPairを生成する
     * @param pubKey 公開鍵
     * @param privKey 秘密鍵
     * @return 生成したKeyPair
     */
    public KeyPair createExistKeyPair(String pubKey, String privKey){
        return Ed25519Sha3.keyPairFromBytes(Hex.decode(privKey), Hex.decode(pubKey));
    }
}
