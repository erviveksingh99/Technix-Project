package com.technix.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
//@Entity
//@Table(name = "tblaudit_logs")
public class AuditLogs {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  int user_id;

    private String table_name;
    private String record_id;
    private  String field_name;
    private  String old_value;
    private  String new_value;
    private LocalDateTime created_at;
}
