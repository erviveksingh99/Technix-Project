package com.technix.repository;

import com.technix.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCompanyId(int companyId);

    List<Product> findByBrandId(int brandId);

    List<Product> findByCategoryId(int category);

    @Query("SELECT p FROM Product p WHERE p.hsnCode = :hsnCode")
    List<Product> findByHsnCode(@Param("hsnCode") String hsnCode);


   /* @Query(value = "SELECT * FROM tblproduct WHERE Product_ID NOT IN (select product_id from tblbill_particulars WHERE billing_date between: startDate AND :endDate)AND p.company_id = :companyId", nativeQuery = true)
    List<Product> findNoSellingProducts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("companyId") int companyId);  //if limit will be required : Pageable pageable

    */

   /* @Query(value = "SELECT * FROM tblproduct WHERE Product_ID NOT IN " +
            "(SELECT product_id FROM tblbill_particulars WHERE billing_date BETWEEN :startDate AND :endDate) " +
            "AND company_id = :companyId", nativeQuery = true)
    List<Product> findNoSellingProducts(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate,
                                        @Param("companyId") int companyId,
                                        @Param("productId") int productId);
*/

   /* @Query(value = "SELECT * FROM tblproduct WHERE Product_ID = :productId " +
            "AND Product_ID NOT IN " +
            "(SELECT product_id FROM tblbill_particulars WHERE billing_date BETWEEN :startDate AND :endDate) " +
            "AND company_id = :companyId", nativeQuery = true)
    List<Product> findNoSellingProducts(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate,
                                        @Param("companyId") int companyId,
                                        @Param("productId") int productId);*/


   /* @Query(value = "SELECT * FROM tblproduct " +
            "WHERE Product_ID = :productId " +
            "AND Product_ID NOT IN " +
            "(SELECT product_id FROM tblbill_particulars " +
            "WHERE billing_date BETWEEN :startDate AND :endDate) " +
            "AND company_id = :companyId", nativeQuery = true)
    List<Product> findNoSellingProducts(
            @Param("productId") int productId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("companyId") int companyId);*/

    @Query(value = "SELECT * FROM tblproduct WHERE Product_ID NOT IN " +
            "(SELECT product_id FROM tblbill_particulars WHERE billing_date BETWEEN :startDate AND :endDate) " +
            "AND company_id = :companyId", nativeQuery = true)
    List<Product> findNoSellingProducts(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate,
                                        @Param("companyId") int companyId);


    // Call the getStock function from MySQL using a native query
    @Query(value = "SELECT getStock(:productId) AS product_stock", nativeQuery = true)
    Double getProductStock(@Param("productId") Integer productId);

//    @Query(value = "SELECT * FROM tblproduct p WHERE :openingStock <= p.recorder_point AND :productId", nativeQuery = true)
//    Product findReorderLevel(@Param("openingStock") double openingStock, int productId);

//    @Query(value = "SELECT * FROM tblproduct p WHERE :openingStock <= p.recorder_point AND p.product_id = :productId", nativeQuery = true)
//    Product findReorderLevel(@Param("openingStock") double openingStock, @Param("productId") int productId);


   /* @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "c.category_name AS categoryName, b.brand_name AS brandName, " +
            "p.selling_rate AS price, p.tax_per AS tax, p.opening_stock AS openingStock " +
            "FROM tblproduct p " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "WHERE p.opening_stock <= :openingStock AND p.product_id = :productId", nativeQuery = true)
    List<Object[]> findReorderLevel(@Param("openingStock") double openingStock, @Param("productId") int productId);
*/

   /* @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "c.category_name AS categoryName, b.brand_name AS brandName, " +
            "p.selling_rate AS price, p.tax_per AS tax, p.opening_stock AS openingStock " +
            "FROM tblproduct p " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "WHERE p.opening_stock <= :openingStock AND p.product_id = :productId", nativeQuery = true)
    List<Object[]> findReorderLevel(@Param("openingStock") double openingStock,
                                    @Param("productId") int productId);*/


    @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "c.category_name AS categoryName, b.brand_name AS brandName, " +
            "p.selling_rate AS price, p.tax_per AS tax, p.recorder_point AS reorderPoint " +
            "FROM tblproduct p " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id " +
            "WHERE :getStock <= p. recorder_point AND p.product_id = :productId", nativeQuery = true)
    List<Object[]> findReorderLevel(@Param("getStock") double openingStock, @Param("productId") int productId);


   /* @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "c.category_name AS categoryName, b.brand_name AS brandName, " +
            "p.selling_rate AS price, p.tax_per AS tax " +  // Add a space after 'tax'
            "FROM tblproduct p " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id", nativeQuery = true)
    List<Object[]> findAllReorderLevel();*/

    @Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, " +
            "c.category_name AS categoryName, b.brand_name AS brandName, " +
            "p.selling_rate AS price, p.tax_per AS tax, p.recorder_point AS reorderPoint " + // Added reorder_point
            "FROM tblproduct p " +
            "LEFT JOIN tblcategory c ON p.category_id = c.category_id " +
            "LEFT JOIN tblbrands b ON p.brand_id = b.brand_id",
            //"WHERE :getStock <= p. recorder_point",
            nativeQuery = true)
    List<Object[]> findAllReorderLevel();
}
