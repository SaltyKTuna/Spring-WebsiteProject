package edu.poly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.asm.domain.Account;

public interface AccountRepo extends JpaRepository<Account, String>{
	
}
