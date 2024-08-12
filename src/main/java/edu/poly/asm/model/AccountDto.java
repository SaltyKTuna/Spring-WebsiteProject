package edu.poly.asm.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private String username;
	
	@NotNull
	@Length(min = 6)
	private String password;
	
	private Boolean isManager = false;
}
