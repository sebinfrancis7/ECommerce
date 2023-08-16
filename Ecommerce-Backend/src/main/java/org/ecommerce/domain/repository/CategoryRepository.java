package org.ecommerce.domain.repository;

import org.ecommerce.domain.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Query("select case when (count(c) > 0) then true else false end from Category c where c.name =:name")
    public boolean checkIfCategoryPresent(@Param("name") String name);

    @Query("select c from Category c where c.name =:name")
    public Category findByName(@Param("name") String name);

    @Query("select c.name from Category c")
    List<String> getAllCategories();
}
