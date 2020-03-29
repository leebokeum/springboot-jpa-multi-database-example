package com.example.multidb.master.repository.mybatis;

import com.example.multidb.master.domain.Master;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MasterMybatisRepository {
    List<Master> findAll() throws Exception;
}
