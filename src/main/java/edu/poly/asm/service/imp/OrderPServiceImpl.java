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

import edu.poly.asm.domain.OrderP;
import edu.poly.asm.repository.OrderPRepo;
import edu.poly.asm.service.OrderPService;

@Service
public class OrderPServiceImpl implements OrderPService{
	
	OrderPRepo orderPRepo;

	public OrderPServiceImpl(OrderPRepo orderPRepo) {
		this.orderPRepo = orderPRepo;
	}

	@Override
	public <S extends OrderP> S save(S entity) {
		return orderPRepo.save(entity);
	}

	@Override
	public <S extends OrderP> List<S> saveAll(Iterable<S> entities) {
		return orderPRepo.saveAll(entities);
	}

	@Override
	public <S extends OrderP> Optional<S> findOne(Example<S> example) {
		return orderPRepo.findOne(example);
	}

	@Override
	public List<OrderP> findAll(Sort sort) {
		return orderPRepo.findAll(sort);
	}

	@Override
	public void flush() {
		orderPRepo.flush();
	}

	@Override
	public Page<OrderP> findAll(Pageable pageable) {
		return orderPRepo.findAll(pageable);
	}

	@Override
	public <S extends OrderP> S saveAndFlush(S entity) {
		return orderPRepo.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderP> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderPRepo.saveAllAndFlush(entities);
	}

	@Override
	public List<OrderP> findAll() {
		return orderPRepo.findAll();
	}

	@Override
	public List<OrderP> findAllById(Iterable<Integer> ids) {
		return orderPRepo.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<OrderP> entities) {
		orderPRepo.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderP> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderPRepo.findAll(example, pageable);
	}

	@Override
	public Optional<OrderP> findById(Integer id) {
		return orderPRepo.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderP> entities) {
		orderPRepo.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return orderPRepo.existsById(id);
	}

	@Override
	public <S extends OrderP> long count(Example<S> example) {
		return orderPRepo.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderPRepo.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderP> boolean exists(Example<S> example) {
		return orderPRepo.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		orderPRepo.deleteAllInBatch();
	}

	@Override
	public OrderP getOne(Integer id) {
		return orderPRepo.getOne(id);
	}

	@Override
	public <S extends OrderP, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderPRepo.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return orderPRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderPRepo.deleteById(id);
	}

	@Override
	public OrderP getById(Integer id) {
		return orderPRepo.getById(id);
	}

	@Override
	public void delete(OrderP entity) {
		orderPRepo.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderPRepo.deleteAllById(ids);
	}

	@Override
	public OrderP getReferenceById(Integer id) {
		return orderPRepo.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderP> entities) {
		orderPRepo.deleteAll(entities);
	}

	@Override
	public <S extends OrderP> List<S> findAll(Example<S> example) {
		return orderPRepo.findAll(example);
	}

	@Override
	public <S extends OrderP> List<S> findAll(Example<S> example, Sort sort) {
		return orderPRepo.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		orderPRepo.deleteAll();
	}
	
	
}
