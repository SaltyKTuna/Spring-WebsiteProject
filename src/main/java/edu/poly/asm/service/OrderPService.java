package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.asm.domain.OrderP;

public interface OrderPService {

	void deleteAll();

	<S extends OrderP> List<S> findAll(Example<S> example, Sort sort);

	<S extends OrderP> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends OrderP> entities);

	OrderP getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(OrderP entity);

	OrderP getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends OrderP, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	OrderP getOne(Integer id);

	void deleteAllInBatch();

	<S extends OrderP> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends OrderP> long count(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<OrderP> entities);

	Optional<OrderP> findById(Integer id);

	<S extends OrderP> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<OrderP> entities);

	List<OrderP> findAllById(Iterable<Integer> ids);

	List<OrderP> findAll();

	<S extends OrderP> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends OrderP> S saveAndFlush(S entity);

	Page<OrderP> findAll(Pageable pageable);

	void flush();

	List<OrderP> findAll(Sort sort);

	<S extends OrderP> Optional<S> findOne(Example<S> example);

	<S extends OrderP> List<S> saveAll(Iterable<S> entities);

	<S extends OrderP> S save(S entity);

}
