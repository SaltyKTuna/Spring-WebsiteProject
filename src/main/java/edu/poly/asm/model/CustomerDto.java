package edu.poly.asm.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable{
	private int customerID;
	@NotNull
	private String name;
	@NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email không hợp lệ")
	private String email;
	@NotNull
	@Length(min = 8)
	private String password;
	@NotNull
	private String phone;
	@NotNull
	private Date registeredDate = new Date();
	private short status;
	
	private Boolean isEdit = false;
}
