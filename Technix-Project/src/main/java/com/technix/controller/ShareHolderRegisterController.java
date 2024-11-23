package com.technix.controller;

import com.technix.entity.ShareHolderRegister;
import com.technix.service.ShareHolderRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shareholder")
public class ShareHolderRegisterController {

    @Autowired
    private ShareHolderRegisterService shareHolderRegisterService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createShareHolder(@RequestBody ShareHolderRegister shareHolder) {
        ShareHolderRegister shareHolderData = shareHolderRegisterService.createShareHolder(shareHolder).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("shareholder", shareHolderData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateShareHolder(@RequestBody ShareHolderRegister shareHolder) {
        ShareHolderRegister updatedShareHolder = shareHolderRegisterService.updateShareHolder(shareHolder).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("shareholder", updatedShareHolder);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getShareHolder/{shareHolderId}")
    public ResponseEntity<Map<String, Object>> getShareHolderById(@RequestParam("shareHolderId") int shareHolderId) {
        ShareHolderRegister shareHolder = shareHolderRegisterService.getShareHolderById(shareHolderId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("shareholder", shareHolder);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getShareHolderCompany/{companyId}")
    public ResponseEntity<Map<String, Object>> getShareHolderByCompanyId(int companyId) {
        List<ShareHolderRegister> shareHolderList = shareHolderRegisterService.getShareHolderByCompanyId(companyId).getBody();
        Map<String, Object> response = new HashMap<>();
        response.put("shareholder", shareHolderList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{shareHolderId}")
    public ResponseEntity<Map<String, Object>> deleteShareHolderById(@RequestParam("shareHolderId") int shareHolderId) {
        return shareHolderRegisterService.deleteShareHolderById(shareHolderId);
    }
}
