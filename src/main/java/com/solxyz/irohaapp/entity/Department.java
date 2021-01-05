package com.solxyz.irohaapp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 部署
 */
@Entity
@Data
@Table(name="department")
public class Department {

    /**
     * 部署番号
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

}
