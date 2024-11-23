package com.technix.service;

import com.technix.entity.Directors;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface DirectorsService {

    public ResponseEntity<Directors> createDirector(Directors directors);

    public ResponseEntity<Directors> updateDirector(Directors directors);

    public ResponseEntity<Directors> getDirectorById(int directorId);

    public ResponseEntity<List<Directors>> getDirectorByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteDirectorById(int directorId);
}
