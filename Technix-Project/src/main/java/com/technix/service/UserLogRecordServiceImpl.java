package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.User;
import com.technix.entity.UserLogRecord;
import com.technix.repository.UserLogRecordRepository;
import com.technix.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserLogRecordServiceImpl implements UserLogRecordService {

    @Autowired
    private UserLogRecordRepository userLogRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    /*
    @Override
    public ResponseEntity<?> createUserLogRecord(HttpServletRequest request) {
        UserLogRecord userLogRecord = new UserLogRecord();
        // this dummy data
        Optional<User> userId = userRepository.findById(5);
        log.info("User id found :{}",userId.get().getUser_id());
        if (userId.isPresent()) {
            userLogRecord.setUser(userId.get());
        } else {
            throw new IdNotFoundException("User id not found");
        }

        // Set basic user log info
        userLogRecord.setIp_address("49.47.132.152");
        userLogRecord.setBrowser(request.getHeader("User-Agent"));
        userLogRecord.setPlatform(request.getHeader("platform"));

        // Make a request to the IPInfo service to get IP details
       // String url = "https://ipinfo.io/" + userLogRecord.getIp_address() + "/json";
        String url = "https://ipinfo.io/" + "49.47.132.152" + "/json";

        Map<String, Object> ipInfoMap = restTemplate.getForObject(url, Map.class);

        log.info("User id found :{}",ipInfoMap.get("city"));

        try {
            if (ipInfoMap != null) {
                // Populate UserLogRecord with IPInfo data
                userLogRecord.setCity((String) ipInfoMap.get("city"));
                userLogRecord.setRegion((String) ipInfoMap.get("region"));
                userLogRecord.setCountry((String) ipInfoMap.get("country"));
                userLogRecord.setPostal((String) ipInfoMap.get("postal"));
                userLogRecord.setLocation((String) ipInfoMap.get("loc")); // "loc" contains latitude,longitude
                userLogRecord.setIsp((String) ipInfoMap.get("org")); // "org" contains ISP info
            }

            // Set login timestamp
            userLogRecord.setLogin_at(LocalDateTime.now());

            // Save the record to the database and return the response
            UserLogRecord savedRecord = userLogRecordRepository.save(userLogRecord);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add the user log record !!", e);
        }

        return ResponseEntity.ok("User log record added !!");
    }
     */

    @Override
    public ResponseEntity<List<UserLogRecord>> getAllLogRecord() {
        return ResponseEntity.ok(userLogRecordRepository.findAll());
    }
}
