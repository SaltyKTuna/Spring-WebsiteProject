package edu.poly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.asm.domain.Product;
import edu.poly.asm.model.ProductDto;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	Page<Product> findByNameContaining(String name, Pageable pageable);
	Page<Product> findByCategoryCategoryID(Integer categoryID, Pageable pageable);
}
