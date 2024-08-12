package edu.poly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.asm.domain.OrderP;

@Repository
public interface OrderPRepo extends JpaRepository<OrderP, Integer>{

}
