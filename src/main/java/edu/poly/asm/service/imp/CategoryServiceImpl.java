package edu.poly.asm.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.asm.domain.Category;
import edu.poly.asm.model.CategoryDto;
import edu.poly.asm.repository.CategoryRepo;
import edu.poly.asm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	CategoryRepo categoryrepo;

	public CategoryServiceImpl(CategoryRepo categoryrepo) {
		this.categoryrepo = categoryrepo;
	}
	
	


	@Override
	public Category findByCategoryID(Integer categoryID) {
		return categoryrepo.findByCategoryID(categoryID);
	}




	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryrepo.findByNameContaining(name, pageable);
	}



	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryrepo.findByNameContaining(name);
	}

	

	@Override
	public <S extends Category> S save(S entity) {
		return categoryrepo.save(entity);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryrepo.saveAll(entities);
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoryrepo.findOne(example);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryrepo.findAll(sort);
	}

	@Override
	public void flush() {
		categoryrepo.flush();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryrepo.findAll(pageable);
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryrepo.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryrepo.saveAllAndFlush(entities);
	}

	@Override
	public List<Category> findAll() {
		return categoryrepo.findAll();
	}

	@Override
	public List<Category> findAllById(Iterable<Integer> ids) {
		return categoryrepo.findAllById(ids);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryrepo.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryrepo.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryrepo.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		categoryrepo.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoryrepo.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		categoryrepo.deleteAllInBatch();
	}

	@Override
	public long count() {
		return categoryrepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryrepo.deleteById(id);
	}

	@Override
	public Category getById(Integer id) {
		return categoryrepo.getById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryrepo.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		categoryrepo.deleteAllById(ids);
	}

	@Override
	public Category getReferenceById(Integer id) {
		return categoryrepo.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryrepo.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryrepo.deleteAll();
	}

	
	
}
