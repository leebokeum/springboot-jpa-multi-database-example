package com.example.multidb.master.repository;

import com.example.multidb.master.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, String> {
}
