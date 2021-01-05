package com.solxyz.irohaapp.entity;

import javax.persistence.*;

import lombok.Data;

/**
 * ユーザー(社員)情報
 */
@Entity
@Data
@Table(name = "employee")
public class UserInfo {

    /**
     * 社員番号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * 名前
     */
    @Column(name = "name")
    private String name;

    /**
     * ログインパスワード（暗号化必須）
     */
    @Column(name = "password")
    private String password;

    @Column(name = "asset_quantity")
    private int quantity;

    @JoinColumn(name = "dep_id")
    @ManyToOne
    private Department dep;

    /**
     * 秘密鍵
     */
    @Column(name = "priv_hex")
    private String priv;

    /**
     * 公開鍵
     */
    @Column(name = "pub_hex")
    private String pub;

}
