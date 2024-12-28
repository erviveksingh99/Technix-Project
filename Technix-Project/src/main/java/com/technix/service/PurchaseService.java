package com.technix.service;

import com.technix.dto.PurchaseDTO;
import com.technix.entity.Purchase;

import javax.sound.midi.InvalidMidiDataException;
import java.util.List;

public interface PurchaseService {
    Purchase createPurchase(PurchaseDTO purchaseDTO) throws InvalidMidiDataException;

//    Purchase updatePurchase(Integer purchaseId, Purchase purchaseDetails);
//
//    Purchase getPurchaseById(Integer purchaseId);
//
//    List<Purchase> getAllPurchases();
//
//    void deletePurchase(Integer purchaseId);
}
