package org.ecommerce.logic.mapper;

import org.mapstruct.*;
import org.ecommerce.domain.model.Product;
import org.ecommerce.domain.dto.ProductDto;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    @Mappings({
            @Mapping(target="id",source="id"),
            @Mapping(target="name",source="name"),
            @Mapping(target="price",source="price"),
            @Mapping(target="quantity",source="quantity"),
            @Mapping(target="imgurl",source="imgurl"),
            @Mapping(target = "category", ignore = true)
    })
    ProductDto toProductDto(Product product);
    @AfterMapping
    default void setCategoryName(Product product, @MappingTarget ProductDto dto){
        dto.setCategory(product.getCategory().getName());
    }
    List<ProductDto> toProductDtoList(List<Product> products);
}
