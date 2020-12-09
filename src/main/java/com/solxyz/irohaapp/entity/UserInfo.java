package com.solxyz.irohaapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
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

    /**
     * 所持している社内通貨の残高
     */
    @Column(name="quantity")
    private double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
