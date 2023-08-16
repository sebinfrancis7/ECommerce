package org.ecommerce.logic.core;

import org.ecommerce.domain.model.Category;
import org.ecommerce.domain.model.Product;
import org.ecommerce.domain.repository.CategoryRepository;
import org.ecommerce.domain.repository.ProductRepository;
import org.ecommerce.logic.kafka.ProductConsumer;
import org.ecommerce.logic.kafka.ProductProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductLogicImpl implements org.ecommerce.logic.api.ProductLogic {

    @Inject
    ProductRepository prodRepo;

    @Inject
    CategoryRepository categoryRepo;

    @Inject
    ProductProducer productProducer;

    @Inject
    ProductConsumer productConsumer;

    @Override
    public Product saveProduct(Product p){
        Category c = null;
        if(categoryRepo.checkIfCategoryPresent(p.getCategory().getName())){
            c = categoryRepo.findByName(p.getCategory().getName());
            p.setCategory(c);
        }else{
            categoryRepo.save(p.getCategory());
        }
        Product savedProduct = prodRepo.save(p);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        prodRepo.findAll().forEach(products::add);
        return products;
    }

    @Override
    public String deleteProduct(int id) {
        String response = "";
        if(prodRepo.existsById(id)){
            prodRepo.deleteById(id);
            response = "product deleted successfully";
        }else{
            response = "cannot find product with given id";
        }
        return response;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product updatedProduct = null;
        if(prodRepo.existsById(id)) {
            LocalDateTime now = LocalDateTime.now();
            Category c = null;
            if(categoryRepo.checkIfCategoryPresent(product.getCategory().getName())){
//                System.out.println("category present");
                c = categoryRepo.findByName(product.getCategory().getName());
            }else{
                c = categoryRepo.save(product.getCategory());
            }
            product.setId(id);
            product.setCategory(c);
            product.setCreatedDateAndTime(now);
            updatedProduct = prodRepo.save(product);
        }
        return updatedProduct;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> filteredProducts = null;
        if(categoryRepo.checkIfCategoryPresent(category)){
            filteredProducts = prodRepo.filterByCategory(category);
        }else{

        }
        return filteredProducts;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = categoryRepo.getAllCategories();
        return categories;
    }

    @Override
    public String updateQuantity(int id) {
        Product p = prodRepo.findById(id).get();
        int quantity = p.getQuantity();
        p.setQuantity(quantity - 1);
        prodRepo.save(p);
        return "Quantity updated";
    }

    @Override
    public Product sendUpdatedProductToKafka(int id, Product product) {
//        Product updatedProduct = null;
        if(prodRepo.existsById(id)) {
            LocalDateTime now = LocalDateTime.now();
            Category c = null;
            if(categoryRepo.checkIfCategoryPresent(product.getCategory().getName())){
//                System.out.println("category present");
                c = categoryRepo.findByName(product.getCategory().getName());
            }else{
                c = categoryRepo.save(product.getCategory());
            }
            product.setId(id);
            product.setCategory(c);
            product.setCreatedDateAndTime(now);
//            updatedProduct = prodRepo.save(product);
        }
//        System.out.println("in product logic");
        productProducer.sendUpdatedProductToKafka(product);
        return product;
    }
}
