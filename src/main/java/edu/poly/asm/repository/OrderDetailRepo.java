package edu.poly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.asm.domain.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{

}
