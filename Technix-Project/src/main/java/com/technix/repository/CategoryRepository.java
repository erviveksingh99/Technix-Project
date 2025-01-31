package com.technix.repository;

import com.technix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM tblcategory where company_id=:companyId and parent_id=:parentCategoryId",nativeQuery = true)
    List<Category> findByCompanyIdAndParentId(int companyId, int parentCategoryId);
}
