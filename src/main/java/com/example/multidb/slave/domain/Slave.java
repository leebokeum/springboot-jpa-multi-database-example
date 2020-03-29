package com.example.multidb.slave.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SLAVE")
public class Slave {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private  String id;

    @Column(name = "column1")
    private String column1;
}
