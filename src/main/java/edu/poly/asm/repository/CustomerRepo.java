package edu.poly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.asm.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	Page<Customer> findByNameContaining(String name, Pageable pageable);
	Customer findByNameContaining(String name);
}
