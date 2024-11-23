package com.technix.repository;

import com.technix.entity.UserLogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRecordRepository extends JpaRepository<UserLogRecord, Integer> {

}
