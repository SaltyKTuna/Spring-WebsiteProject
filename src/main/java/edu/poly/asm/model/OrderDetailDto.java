package edu.poly.asm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
	private int orderDetailID;
	private int orderID;
	private int productID;
	private int quantity;
	private double unitPrice;
}
