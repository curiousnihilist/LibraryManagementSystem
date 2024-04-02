package com.akash.lms.repo;


import com.akash.lms.enitities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
