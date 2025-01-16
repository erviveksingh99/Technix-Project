package com.technix.service;

import com.technix.dto.PurchaseDTO;
import com.technix.entity.Purchase;
import org.springframework.http.ResponseEntity;
import javax.sound.midi.InvalidMidiDataException;
import java.util.Map;

public interface PurchaseService {
    Purchase createPurchase(PurchaseDTO purchaseDTO) throws InvalidMidiDataException;

    Purchase updatePurchase(Integer purchaseId, PurchaseDTO purchaseDTO);

    Purchase getPurchaseById(Integer purchaseId);

    ResponseEntity<Map<String, Object>> deletePurchase(Integer purchaseId);


}
