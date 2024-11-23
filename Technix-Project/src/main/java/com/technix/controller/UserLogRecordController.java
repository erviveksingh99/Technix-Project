package com.technix.controller;

import com.technix.entity.UserLogRecord;
import com.technix.service.UserLogRecordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ipinfo")
public class UserLogRecordController {

    @Autowired
    private UserLogRecordService userLogRecordService;

    /*
    @PostMapping("/add")
    public ResponseEntity<?> createUserLogRecord(HttpServletRequest request)
    {
        return userLogRecordService.createUserLogRecord(request);
    }

     */

    @GetMapping("/getAll")
    public ResponseEntity<List<UserLogRecord>> getAllLogRecord()
    {
        return userLogRecordService.getAllLogRecord();
    }
}
