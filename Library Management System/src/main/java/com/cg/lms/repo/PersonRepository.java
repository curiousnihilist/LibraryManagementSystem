package com.cg.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.lms.dto.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
