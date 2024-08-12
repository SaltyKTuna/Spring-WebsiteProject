package edu.poly.asm.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderP")
public class OrderP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID;

	@Temporal(TemporalType.DATE)
	@Column(name = "orderDate")
	private Date orderDate = new Date();

	@Column(name = "amount")
	private double amount;

	@Column(name = "status")
	private short status;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "orderP")
	private List<OrderDetail> orderDetails;
}
