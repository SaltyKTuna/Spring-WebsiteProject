package edu.poly.asm.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPDto {
	private int orderID;
	private Date orderDate = new Date();
	private double amount;
	private short status;
	private int customerID;
}
