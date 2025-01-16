package com.technix.service;

import com.technix.dto.PurchaseDTO;
import com.technix.entity.Purchase;
import org.springframework.http.ResponseEntity;
import javax.sound.midi.InvalidMidiDataException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PurchaseService {
    Purchase createPurchase(PurchaseDTO purchaseDTO) throws InvalidMidiDataException;

    Purchase updatePurchase(Integer purchaseId, PurchaseDTO purchaseDTO);

    Purchase getPurchaseById(Integer purchaseId);

    List<Map<String, Object>> getRegisteredPurchasedSupplier(String taxationType, LocalDate startDate, LocalDate endDate);

    ResponseEntity<Map<String, Object>> deletePurchase(Integer purchaseId);


}
