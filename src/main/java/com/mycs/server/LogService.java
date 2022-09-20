package com.mycs.server;

import com.mycs.entities.DBLog;
import com.mycs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    public void save(DBLog DBLog) { logRepository.save(DBLog); }
}
