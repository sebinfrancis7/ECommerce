package org.ecommerce.domain.repository;

import org.ecommerce.domain.dto.ProductDto;
import org.ecommerce.domain.model.Category;
import org.ecommerce.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("select p from  Product p where p.category.name =:name")
    public List<Product> filterByCategory(@Param("name") String name);
}
