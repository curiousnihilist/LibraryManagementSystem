package com.cg.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.IssuedItem;

@Repository
public interface IssuedItemRepository extends JpaRepository<IssuedItem, Integer>{

}
