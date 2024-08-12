package edu.poly.asm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.asm.domain.Category;
import edu.poly.asm.model.CategoryDto;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	List<Category> findByNameContaining(String name);
	Page<Category> findByNameContaining(String name, Pageable pageable);
	Category findByCategoryID(Integer categoryID);
}
