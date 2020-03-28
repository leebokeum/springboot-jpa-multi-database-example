package com.example.multidb.slave.repository;

import com.example.multidb.slave.domain.Slave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaveRepository extends JpaRepository<Slave, String> {
}
