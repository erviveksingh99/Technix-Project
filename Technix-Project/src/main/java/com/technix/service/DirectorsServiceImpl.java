package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Directors;
import com.technix.repository.DirectorsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DirectorsServiceImpl implements DirectorsService {

    @Autowired
    private DirectorsRepository directorsRepo;

    @Override
    public ResponseEntity<Directors> createDirector(Directors directors) {
        try {
            directors.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(directorsRepo.save(directors));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<Directors> updateDirector(Directors directors) {
        Optional<Directors> existingDirectors = directorsRepo.findById(directors.getId());
        if (existingDirectors.isPresent()) {
            Directors updateDirector = existingDirectors.get();

            // BeanUtils.copyProperties() is optional if we don't want to set and get manually then we can use this method.
            // Copy non-null properties from the incoming bankDetails to the existing one
            BeanUtils.copyProperties(directors, updateDirector, "createdAt"); // Exclude fields like id, createdAt if necessary
            updateDirector.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(directorsRepo.save(updateDirector));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Directors> getDirectorById(int directorId) {
        return ResponseEntity.ok(directorsRepo.findById(directorId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<Directors>> getDirectorByCompanyId(int companyId) {
        List<Directors> directorsList = directorsRepo.findByCompanyId(companyId);
        if (!directorsList.isEmpty()) {
            return ResponseEntity.ok(directorsList);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteDirectorById(int directorId) {
        Optional<Directors> directors = directorsRepo.findById(directorId);
        if (directors.isPresent()) {
            directorsRepo.deleteById(directorId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Directors data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
