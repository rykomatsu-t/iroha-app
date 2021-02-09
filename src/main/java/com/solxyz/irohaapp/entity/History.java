package com.solxyz.irohaapp.entity;

import com.google.type.Money;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
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
    private Timestamp sendTime;

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
    @JoinColumn(name = "receive_id")
    @ManyToOne
    private UserInfo receiveId;

    /**
     * 送信するアセットの量
     */
    @Column(name = "val")
    private Double val;
}
