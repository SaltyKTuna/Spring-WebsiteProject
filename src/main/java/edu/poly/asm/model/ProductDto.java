package edu.poly.asm.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productID;
    private String name;
    private int quantity;
    private double unitPrice;
    private String image; // lưu tên file
    private String description;
    private double discount;
    private Date enterDate = new Date();
    private short status;
    @NotNull(message = "Category is required")
    private int categoryID;
    private Boolean isEdit = false;
    private MultipartFile imageFile;
    
    
}
