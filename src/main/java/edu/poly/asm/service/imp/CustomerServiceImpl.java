package edu.poly.asm.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.asm.domain.Customer;
import edu.poly.asm.repository.CustomerRepo;
import edu.poly.asm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	CustomerRepo customerRepo;

	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	

	@Override
	public Customer findByNameContaining(String name) {
		return customerRepo.findByNameContaining(name);
	}



	@Override
	public Page<Customer> findByNameContaining(String name, Pageable pageable) {
		return customerRepo.findByNameContaining(name, pageable);
	}



	@Override
	public <S extends Customer> S save(S entity) {
		return customerRepo.save(entity);
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		return customerRepo.saveAll(entities);
	}

	@Override
	public <S extends Customer> Optional<S> findOne(Example<S> example) {
		return customerRepo.findOne(example);
	}

	@Override
	public List<Customer> findAll(Sort sort) {
		return customerRepo.findAll(sort);
	}

	@Override
	public void flush() {
		customerRepo.flush();
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepo.findAll(pageable);
	}

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		return customerRepo.saveAndFlush(entity);
	}

	@Override
	public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
		return customerRepo.saveAllAndFlush(entities);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> findAllById(Iterable<Integer> ids) {
		return customerRepo.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Customer> entities) {
		customerRepo.deleteInBatch(entities);
	}

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerRepo.findAll(example, pageable);
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		return customerRepo.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Customer> entities) {
		customerRepo.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return customerRepo.existsById(id);
	}

	@Override
	public <S extends Customer> long count(Example<S> example) {
		return customerRepo.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		customerRepo.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> example) {
		return customerRepo.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		customerRepo.deleteAllInBatch();
	}

	@Override
	public Customer getOne(Integer id) {
		return customerRepo.getOne(id);
	}

	@Override
	public <S extends Customer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return customerRepo.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return customerRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		customerRepo.deleteById(id);
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepo.getById(id);
	}

	@Override
	public void delete(Customer entity) {
		customerRepo.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		customerRepo.deleteAllById(ids);
	}

	@Override
	public Customer getReferenceById(Integer id) {
		return customerRepo.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		customerRepo.deleteAll(entities);
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example) {
		return customerRepo.findAll(example);
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		return customerRepo.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		customerRepo.deleteAll();
	}
	
	
}
