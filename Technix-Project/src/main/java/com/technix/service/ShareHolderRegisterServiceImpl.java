package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.ShareHolderRegister;
import com.technix.entity.Unit;
import com.technix.repository.ShareHolderRegisterRepository;
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
public class ShareHolderRegisterServiceImpl implements ShareHolderRegisterService {

    @Autowired
    private ShareHolderRegisterRepository shareHolderRegisterRepo;

    @Override
    public ResponseEntity<ShareHolderRegister> createShareHolder(ShareHolderRegister shareHolder) {
        try {
            shareHolder.setDateOfAllotment(LocalDateTime.now());
            shareHolder.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(shareHolderRegisterRepo.save(shareHolder));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
        }
    }

    @Override
    public ResponseEntity<ShareHolderRegister> updateShareHolder(ShareHolderRegister shareHolder) {
        Optional<ShareHolderRegister> holderRegister = shareHolderRegisterRepo.findById(shareHolder.getId());
        if (holderRegister.isPresent()) {
            ShareHolderRegister updateShareHolder = holderRegister.get();
            updateShareHolder.setId(shareHolder.getId());
            updateShareHolder.setCompanyId(shareHolder.getCompanyId());
            updateShareHolder.setShareholderName(shareHolder.getShareholderName());
            updateShareHolder.setDesignation(shareHolder.getDesignation());
            updateShareHolder.setNumberOfShares(shareHolder.getNumberOfShares());
            updateShareHolder.setShareValue(shareHolder.getShareValue());
            updateShareHolder.setDateOfAllotment(LocalDateTime.now());
            updateShareHolder.setAddress(shareHolder.getAddress());
            updateShareHolder.setCity(shareHolder.getCity());
            updateShareHolder.setPin(shareHolder.getPin());
            updateShareHolder.setState(shareHolder.getState());
            updateShareHolder.setCountry(shareHolder.getCountry());
            updateShareHolder.setPan(shareHolder.getPan());
            updateShareHolder.setAadhar(shareHolder.getAadhar());
            updateShareHolder.setMobileNumber(shareHolder.getMobileNumber());
            updateShareHolder.setEmail(shareHolder.getEmail());
            updateShareHolder.setNominee(shareHolder.getNominee());
            updateShareHolder.setCreatedBy(shareHolder.getCreatedBy());
            updateShareHolder.setCreatedAt(LocalDateTime.now());
            try {
                return ResponseEntity.ok(shareHolderRegisterRepo.save(updateShareHolder));
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database communication failed");
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<ShareHolderRegister> getShareHolderById(int shareHolderId) {
        return ResponseEntity.ok(shareHolderRegisterRepo.findById(shareHolderId).orElseThrow(() -> new IdNotFoundException("Id not found")));
    }

    @Override
    public ResponseEntity<List<ShareHolderRegister>> getShareHolderByCompanyId(int companyId) {
        List<ShareHolderRegister> shareHolderList = shareHolderRegisterRepo.findByCompanyId(companyId);
        if (!shareHolderList.isEmpty()) {
            return ResponseEntity.ok(shareHolderList);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteShareHolderById(int shareHolderId) {
        Optional<ShareHolderRegister> shareHolder = shareHolderRegisterRepo.findById(shareHolderId);
        if (shareHolder.isPresent()) {
            shareHolderRegisterRepo.deleteById(shareHolderId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("message", "Share holder data is deleted");
            return ResponseEntity.ok(response);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
