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

    List<Map<String, Object>> getPartyWiseProductPurchaseBill(String partyName, String productName, LocalDate startDate, LocalDate endDate);

    List<Map<String, Object>> getAllPartyWiseProductPurchaseBill(LocalDate startDate, LocalDate endDate);

    List<Purchase> purchaseRegisterProductWise(int productId);

    List<Purchase> purchaseRegisterCategoryWise(int categoryId);

    List<Purchase> PurchaseRegisterBrandWise(int brandId);

    ResponseEntity<Map<String, Object>> deletePurchase(Integer purchaseId);
}
