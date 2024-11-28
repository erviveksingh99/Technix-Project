package com.technix.service;

import com.technix.entity.Ledger;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface LedgerService {

    public ResponseEntity<Ledger> createLedger(Ledger ledger);

    public ResponseEntity<Ledger> updateLedger(Ledger ledger);

    public ResponseEntity<Ledger> getLedgerById(int ledgerId);

    public ResponseEntity<List<Ledger>> getLedgerByCompanyId(int companyId);

    public ResponseEntity<Map<String, Object>> deleteLedgerById(int ledgerId);
}
