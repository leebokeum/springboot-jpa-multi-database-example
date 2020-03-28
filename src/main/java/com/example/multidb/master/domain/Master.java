package com.example.multidb.master.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MASTER")
public class Master {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "column1")
    private  String column1;

    @Column(name = "column2")
    private String column2;
}
