package org.ecommerce.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.domain.model.Category;
@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imgurl;
    private String category;
}
