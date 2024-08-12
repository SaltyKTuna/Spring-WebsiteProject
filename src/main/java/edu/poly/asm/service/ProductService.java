package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.asm.domain.Product;
import edu.poly.asm.model.ProductDto;

public interface ProductService {

	void deleteAll();

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Product> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Product> entities);

	Product getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Product entity);

	Product getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Product getOne(Integer id);

	void deleteAllInBatch();

	<S extends Product> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Product> long count(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Product> entities);

	Optional<Product> findById(Integer id);

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Product> entities);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll();

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Product> S saveAndFlush(S entity);

	Page<Product> findAll(Pageable pageable);

	void flush();

	List<Product> findAll(Sort sort);

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	<S extends Product> S save(S entity);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findByCategoryCategoryID(Integer categoryID, Pageable pageable);


}
