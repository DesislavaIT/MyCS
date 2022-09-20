package com.mycs.repository;

import com.mycs.entities.DBLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<DBLog, Long> {
}
