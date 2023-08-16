package org.ecommerce.logic.api;

import org.ecommerce.domain.model.Product;

import java.util.List;

public interface ProductLogic {
    Product saveProduct(Product p);

    List<Product> getAllProducts();

    String deleteProduct(int id);

    Product updateProduct(int id, Product product);

    List<Product> getProductsByCategory(String category);

    List<String> getAllCategories();

    String updateQuantity(int id);

    Product sendUpdatedProductToKafka(int id, Product product);
}
