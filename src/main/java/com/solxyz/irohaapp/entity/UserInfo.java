package com.solxyz.irohaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserInfo {

    /**
     * 社員番号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name="password")
    private String password;
}
