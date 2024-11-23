package com.technix.repository;

import com.technix.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
    List<Contacts> findByCompanyId(int companyId);
}
