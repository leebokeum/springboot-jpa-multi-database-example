package com.example.multidb.test;

import com.example.multidb.master.repository.jpa.MasterRepository;
import com.example.multidb.master.repository.mybatis.MasterMybatisRepository;
import com.example.multidb.slave.repository.jpa.SlaveRepository;
import com.example.multidb.slave.repository.mybatis.SlaveMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    final private MasterRepository masterRepository;
    final private SlaveRepository slaveRepository;
    final private MasterMybatisRepository masterMybatisRepository;
    final private SlaveMybatisRepository slaveMybatisRepository;

    @Autowired
    public TestRestController(MasterRepository masterRepository, SlaveRepository slaveRepository, MasterMybatisRepository masterMybatisRepository, SlaveMybatisRepository slaveMybatisRepository) {
        this.masterRepository = masterRepository;
        this.slaveRepository = slaveRepository;
        this.masterMybatisRepository = masterMybatisRepository;
        this.slaveMybatisRepository = slaveMybatisRepository;
    }

    @GetMapping("/jpa-db1")
    ResponseEntity jpaDb1 (){
        return new ResponseEntity<>(masterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/jpa-db2")
    ResponseEntity jpaDb2 (){
        return new ResponseEntity<>(slaveRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/mybatis-db1")
    ResponseEntity mybatisDb1 () throws Exception {
        return new ResponseEntity<>(masterMybatisRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/mybatis-db2")
    ResponseEntity mybatisDb2 () throws Exception {
        return new ResponseEntity<>(slaveMybatisRepository.findAll(), HttpStatus.OK);
    }


}
