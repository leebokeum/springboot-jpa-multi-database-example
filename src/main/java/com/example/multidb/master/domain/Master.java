package com.example.multidb.master.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MASTER")
public class Master {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private  String id;

    @Column(name = "column1")
    private String column1;
}
