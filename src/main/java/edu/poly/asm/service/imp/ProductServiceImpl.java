package edu.poly.asm.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.asm.domain.Product;
import edu.poly.asm.model.ProductDto;
import edu.poly.asm.repository.ProductRepo;
import edu.poly.asm.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	ProductRepo productRepo;

	public ProductServiceImpl(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	



	@Override
	public Page<Product> findByCategoryCategoryID(Integer categoryID, Pageable pageable) {
		return productRepo.findByCategoryCategoryID(categoryID, pageable);
	}





	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRepo.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepo.save(entity);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productRepo.saveAll(entities);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productRepo.findOne(example);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepo.findAll(sort);
	}

	@Override
	public void flush() {
		productRepo.flush();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productRepo.saveAndFlush(entity);
	}

	@Override
	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productRepo.saveAllAndFlush(entities);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		return productRepo.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		productRepo.deleteInBatch(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepo.findAll(example, pageable);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Product> entities) {
		productRepo.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return productRepo.existsById(id);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return productRepo.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		productRepo.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productRepo.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		productRepo.deleteAllInBatch();
	}

	@Override
	public Product getOne(Integer id) {
		return productRepo.getOne(id);
	}

	@Override
	public <S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productRepo.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		productRepo.deleteById(id);
	}

	@Override
	public Product getById(Integer id) {
		return productRepo.getById(id);
	}

	@Override
	public void delete(Product entity) {
		productRepo.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		productRepo.deleteAllById(ids);
	}

	@Override
	public Product getReferenceById(Integer id) {
		return productRepo.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		productRepo.deleteAll(entities);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return productRepo.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepo.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		productRepo.deleteAll();
	}
	
	
}
