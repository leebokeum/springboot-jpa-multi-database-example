package com.example.multidb.slave.repository.mybatis;

import com.example.multidb.slave.domain.Slave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SlaveMybatisRepository {
    List<Slave> findAll() throws Exception;
}
