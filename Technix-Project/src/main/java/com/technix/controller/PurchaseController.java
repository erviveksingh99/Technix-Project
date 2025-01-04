package com.technix.controller;

import com.technix.dto.PurchaseDTO;
import com.technix.entity.Purchase;
import com.technix.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sound.midi.InvalidMidiDataException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPurchase(@RequestBody PurchaseDTO purchaseDTO) throws InvalidMidiDataException {
        Purchase newPurchase = purchaseService.createPurchase(purchaseDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("data", newPurchase);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> updatePurchase(@PathVariable Integer id, @RequestBody PurchaseDTO purchaseDTO) {
        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchaseDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("data", updatedPurchase);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPurchase/{purchaseId}")
    public ResponseEntity<Map<String, Object>> getPurchaseById(@PathVariable("purchaseId") Integer purchaseId) {
        Purchase purchaseResponse = purchaseService.getPurchaseById(purchaseId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", purchaseResponse);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity<Map<String, Object>> deletePurchase(@PathVariable("purchaseId") Integer purchaseId) {
        return purchaseService.deletePurchase(purchaseId);
    }
}
