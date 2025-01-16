package com.technix.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.technix.dto.ProductSalesDTO;
import com.technix.dto.Views;
import com.technix.entity.Bill;
import com.technix.entity.Product;
import com.technix.entity.TransactionDetails;
import com.technix.repository.ProductRepository;
import com.technix.service.BillService;
import com.technix.service.ContactsService;
import com.technix.service.ProductService;
import com.technix.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private TransactionDetailsService detailsService;

    @GetMapping("/getCashierWiseReport")
    public ResponseEntity<Map<String, Object>> getCashierWiseReport(@RequestParam("contactId") int contactId,
                                                                    @RequestParam("startDate") LocalDate startDate,
                                                                    @RequestParam("endDate") LocalDate endDate) {
        List<Bill> billList = billService.getCashierWiseReport(contactId, startDate, endDate);
        Map<String, Object> response = new HashMap<>();
        response.put("data", billList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lowSellingProduct")
    public ResponseEntity<Map<String, Object>> getLowestSellingProducts(@RequestParam("startDate") LocalDate startDate,
                                                                        @RequestParam("endDate") LocalDate endDate,
                                                                        @RequestParam("companyId") int companyId) {
        List<ProductSalesDTO> billData = billService.getLowestSellingProducts(startDate, endDate, companyId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", billData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/topSellingProduct")
    public ResponseEntity<Map<String, Object>> getTopSellingProduct(@RequestParam("startDate") LocalDate startDate,
                                                                    @RequestParam("endDate") LocalDate endDate,
                                                                    @RequestParam("companyId") int companyId) {
        List<ProductSalesDTO> billData = billService.getTopSellingProduct(startDate, endDate, companyId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", billData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/noSellingProduct")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getNoSellingProduct(@RequestParam("startDate") LocalDate startDate,
                                                                   @RequestParam("endDate") LocalDate endDate,
                                                                   @RequestParam("companyId") int companyId) {
        List<Product> billData = billService.getNoSellingProduct(startDate, endDate, companyId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", billData);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getItemWiseMasterReport/{companyId}")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getItemWiseMasterReport(@PathVariable("companyId") int companyId) {
        List<Product> productList = productService.getItemWiseMasterReport(companyId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBrandWiseMasterReport/{brandId}")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getBrandWiseMasterReport(@PathVariable int brandId) {
        List<Product> productList = productService.getBrandWiseMasterReport(brandId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProductGroupWise/{categoryId}")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getProductGroupWise(@PathVariable int categoryId) {
        List<Product> productList = productService.getProductGroupWise(categoryId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getHsnCodeWise/{HsnCode}")
    @JsonView(Views.ParentView.class)
    public ResponseEntity<Map<String, Object>> getHsnCodeWise(@PathVariable String HsnCode) {
        List<Product> productList = productService.getHsnCodeWise(HsnCode);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSalesReportGstWise/{taxationType}")
    public ResponseEntity<Map<String, Object>> listOfSalesRegisteredCustomer(@PathVariable String taxationType) {
        List<Map<String, Object>> contactsList = contactsService.listOfSalesRegisteredCustomer(taxationType);
        Map<String, Object> response = new HashMap<>();
        response.put("data", contactsList);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listOfVoucher")
    public ResponseEntity<Map<String, Object>> getListOfVoucherReport(@RequestParam("startDate") LocalDate startDate,
                                                                      @RequestParam("endDate") LocalDate endDate,
                                                                      @RequestParam("voucherType") String voucherType) {
        List<Map<String, Object>> listOfVoucher = detailsService.getListOfVoucherReport(startDate, endDate, voucherType);
        Map<String, Object> response = new HashMap<>();
        response.put("data", listOfVoucher);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAllVoucherTypeTransaction")
    public ResponseEntity<Map<String, Object>> findAllVoucherTypeTransaction(@RequestParam("startDate") LocalDate startDate,
                                                                             @RequestParam("endDate") LocalDate endDate) {
        List<Map<String, Object>> listOfVoucher = detailsService.findAllVoucherTypeTransaction(startDate, endDate);
        Map<String, Object> response = new HashMap<>();
        response.put("data", listOfVoucher);
        response.put("status", true);
        return ResponseEntity.ok(response);
    }
}
