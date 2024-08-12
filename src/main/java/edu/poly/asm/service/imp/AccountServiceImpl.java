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

import edu.poly.asm.domain.Account;
import edu.poly.asm.repository.AccountRepo;
import edu.poly.asm.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	AccountRepo accountrepo;

	public AccountServiceImpl(AccountRepo accountrepo) {
		this.accountrepo = accountrepo;
	}

	@Override
	public <S extends Account> S save(S entity) {
		return accountrepo.save(entity);
	}

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountrepo.saveAll(entities);
	}

	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return accountrepo.findOne(example);
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountrepo.findAll(sort);
	}

	@Override
	public void flush() {
		accountrepo.flush();
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountrepo.findAll(pageable);
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return accountrepo.saveAndFlush(entity);
	}

	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountrepo.saveAllAndFlush(entities);
	}

	@Override
	public List<Account> findAll() {
		return accountrepo.findAll();
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return accountrepo.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountrepo.deleteInBatch(entities);
	}

	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountrepo.findAll(example, pageable);
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountrepo.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountrepo.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return accountrepo.existsById(id);
	}

	@Override
	public <S extends Account> long count(Example<S> example) {
		return accountrepo.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountrepo.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		return accountrepo.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		accountrepo.deleteAllInBatch();
	}

	@Override
	public Account getOne(String id) {
		return accountrepo.getOne(id);
	}

	@Override
	public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return accountrepo.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return accountrepo.count();
	}

	@Override
	public void deleteById(String id) {
		accountrepo.deleteById(id);
	}

	@Override
	public Account getById(String id) {
		return accountrepo.getById(id);
	}

	@Override
	public void delete(Account entity) {
		accountrepo.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountrepo.deleteAllById(ids);
	}

	@Override
	public Account getReferenceById(String id) {
		return accountrepo.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		accountrepo.deleteAll(entities);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		return accountrepo.findAll(example);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		return accountrepo.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		accountrepo.deleteAll();
	}
	
	
}
