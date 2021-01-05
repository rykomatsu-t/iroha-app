package com.solxyz.irohaapp.entity;

import com.google.type.Money;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 取引履歴
 */
@Entity
@Data
@Table(name = "history")
public class History {
    /**
     * 履歴番号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * 送信日時
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * メッセージ
     */
    @Column(name = "message")
    private String message;

    /**
     * 送信するアセット
     */
    @JoinColumn(name = "asset_id")
    @ManyToOne
    private Asset assetId;

    /**
     * 送信元ユーザー
     */
    @JoinColumn(name = "send_id")
    @ManyToOne
    private UserInfo sendId;


    /**
     * 送信先ユーザー
     */
    @JoinColumn(name = "recieve_id")
    @ManyToOne
    private UserInfo recieveId;

    /**
     * 送信するアセットの量
     */
    @Column(name = "val")
    private Double val;
}
