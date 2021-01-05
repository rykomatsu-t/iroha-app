package com.solxyz.irohaapp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * アセット
 */
@Entity
@Data
@Table(name="asset")
public class Asset {
    /**
     * アセット番号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * アセット名
     */
    @Column(name = "name")
    private String name;

    @Column(name = "status_code")
    private int statusCode;
}
