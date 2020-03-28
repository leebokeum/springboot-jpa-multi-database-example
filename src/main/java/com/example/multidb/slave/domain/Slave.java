package com.example.multidb.slave.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SLAVE")
public class Slave {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "column1")
    private  String column1;

    @Column(name = "column2")
    private String column2;
}
