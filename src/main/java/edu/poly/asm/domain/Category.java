package edu.poly.asm.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
