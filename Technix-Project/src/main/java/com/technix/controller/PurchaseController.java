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

 /*@PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Integer id, @RequestBody Purchase purchaseDetails) {
        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchaseDetails);
        return ResponseEntity.ok(updatedPurchase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Integer id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
*/
}
