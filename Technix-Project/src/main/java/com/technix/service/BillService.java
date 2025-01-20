package com.technix.service;

import com.technix.dto.BillDTO;
import com.technix.dto.ProductSalesDTO;
import com.technix.entity.Bill;
import com.technix.entity.Product;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BillService {

    public ResponseEntity<Bill> createBill(BillDTO bill);

    public ResponseEntity<Bill> updateBill(int billId, BillDTO billDTO);

    public ResponseEntity<Bill> getBillById(int billId);

    public ResponseEntity<List<Bill>> getBillByCompanyId(int companyId);

    public List<Bill> getCashierWiseReport(int contactId, LocalDate startDate, LocalDate endDate) ;

    public List<ProductSalesDTO> getTopSellingProduct(LocalDate startDate, LocalDate endDate, int companyId);

    public List<ProductSalesDTO> getLowestSellingProducts(LocalDate startDate, LocalDate endDate, int companyId);

    public List<Product> getNoSellingProduct(LocalDate startDate, LocalDate endDate,int companyId);


    public List<Bill> salesRegisterProductWise(int productId);

    public ResponseEntity<Map<String, Object>> deleteBillById(int billId);
}
