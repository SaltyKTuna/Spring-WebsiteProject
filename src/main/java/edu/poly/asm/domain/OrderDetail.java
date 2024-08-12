package edu.poly.asm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailID;
	
	@ManyToOne
	@JoinColumn(name = "orderID", nullable = false)
	private OrderP orderP;
	
	@ManyToOne
	@JoinColumn(name = "productID", nullable = false)
	private Product product;
	
	private int quantity;

	private double unitPrice;
}
