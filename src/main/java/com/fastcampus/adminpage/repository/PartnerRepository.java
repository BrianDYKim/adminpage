package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.Category;
import com.fastcampus.adminpage.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByCategory(Category category);
}
