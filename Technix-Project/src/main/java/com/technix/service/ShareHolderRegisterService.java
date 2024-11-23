package com.technix.service;

import com.technix.entity.ShareHolderRegister;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface ShareHolderRegisterService {

    public ResponseEntity<ShareHolderRegister> createShareHolder(ShareHolderRegister shareHolder);

    public ResponseEntity<ShareHolderRegister> updateShareHolder(ShareHolderRegister shareHolder);

    public ResponseEntity<ShareHolderRegister> getShareHolderById(int shareHolderId);

    public ResponseEntity<List<ShareHolderRegister>> getShareHolderByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteShareHolderById(int shareHolderId);
}
