package com.technix.repository;

import com.technix.dto.ProductSalesDTO;
import com.technix.entity.BillParticulars;
import com.technix.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillParticularsRepository extends JpaRepository<BillParticulars, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BillParticulars bp WHERE bp.billId = :billId")
    void deleteByBillId(@Param("billId") int billId);

    /*@Query("SELECT new com.technix.dto.ProductSalesDTO(p.productId, p.productName, SUM(bp.quantity)) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName " +
            "ORDER BY SUM(bp.quantity) ASC")
    List<Object[]> findLowestSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);*/

   /* @Query("SELECT p.productId, p.productName, SUM(bp.quantity) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName " +
            "ORDER BY SUM(bp.quantity) ASC")
    List<Object[]> findLowestSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);*/

   /* @Query("SELECT(" +
            "p.productId, p.productName, b.brandName, c.categoryName, SUM(bp.quantity)) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "LEFT JOIN Brand b ON p.brandId = b.brandId " +
            "LEFT JOIN Category c ON p.categoryId = c.categoryId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName, b.brandName, c.categoryName " +
            "ORDER BY SUM(bp.quantity) ASC")
    List<Object[]> findLowestSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
*/
    /*@Query("SELECT(" +
            "p.productId, p.productName, b.brandName, c.categoryName, SUM(bp.quantity)) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "LEFT JOIN Brand b ON p.brandId = b.brandId " +
            "LEFT JOIN Category c ON p.categoryId = c.categoryId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName, b.brandName, c.categoryName " +
            "ORDER BY SUM(bp.quantity) DESC")
    List<Object[]> findTopSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
*/



    /*@Query("SELECT (" +
            "p.productId, p.productName, b.brandName, c.categoryName, SUM(bp.quantity)) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "LEFT JOIN Brand b ON p.brandId = b.brandId " +
            "LEFT JOIN Category c ON p.categoryId = c.categoryId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName, b.brandName, c.categoryName " +
            "ORDER BY SUM(bp.quantity) DESC")
    List<Object[]> findTopSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);*/

   /* @Query("SELECT (" +
            "p.productId, p.productName, b.brandName, c.categoryName, SUM(bp.quantity)) " +
            "FROM BillParticulars bp " +
            "LEFT JOIN Product p ON bp.productId = p.productId " +
            "LEFT JOIN Brand b ON p.brandId = b.brandId " +
            "LEFT JOIN Category c ON p.categoryId = c.categoryId " +
            "WHERE bp.billingDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.productId, p.productName, b.brandName, c.categoryName " +
            "ORDER BY p.productId DESC")
    List<Object[]> findTopSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);*/

   /* @Query(value = "SELECT p.product_id, p.product_name, b.brand_name, c.category_name, SUM(bp.quantity) AS total_quantity " +
            "FROM tblbill_particulars bp " +
            "LEFT JOIN tblproduct p ON bp.product_id = p.product_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "WHERE bp.billing_date BETWEEN :startDate AND :endDate " +
            "GROUP BY p.product_id, p.product_name, b.brand_name, c.category_name " +
            "ORDER BY total_quantity DESC " +
            "LIMIT 4", nativeQuery = true)
    List<ProductSalesDTO> findTopSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);*/


    @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "b.brand_name AS brandName, c.category_name AS categoryName, " +
            "SUM(bp.quantity) AS totalQuantity " +
            "FROM tblbill_particulars bp " +
            "LEFT JOIN tblproduct p ON bp.product_id = p.product_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "WHERE bp.billing_date BETWEEN :startDate AND :endDate " +
            "AND p.company_id = :companyId " +  // Validate with company_id
            "GROUP BY p.product_id, p.product_name, b.brand_name, c.category_name " +
            "ORDER BY totalQuantity DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Object[]> findTopSellingProductsByCompanyId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("companyId") int companyId);


    @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "b.brand_name AS brandName, c.category_name AS categoryName, " +
            "SUM(bp.quantity) AS totalQuantity " +
            "FROM tblbill_particulars bp " +
            "LEFT JOIN tblproduct p ON bp.product_id = p.product_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "WHERE bp.billing_date BETWEEN :startDate AND :endDate " +
            "AND p.company_id = :companyId " +  // Validate with company_id
            "GROUP BY p.product_id, p.product_name, b.brand_name, c.category_name " +
            "ORDER BY totalQuantity ASC " +
            "LIMIT 4", nativeQuery = true)
    List<Object[]> findLowestSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("companyId") int companyId);

}
