package com.technix.service;

import com.technix.entity.UserLogRecord;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserLogRecordService {

   // public ResponseEntity<?> createUserLogRecord(HttpServletRequest request);

    public ResponseEntity<List<UserLogRecord>> getAllLogRecord();

}
