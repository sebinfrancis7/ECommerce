package org.ecommerce.service.core;

import org.ecommerce.logic.mapper.ProductMapper;
import org.ecommerce.domain.model.Product;
import org.ecommerce.domain.dto.ProductDto;
import org.ecommerce.logic.api.ProductLogic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ProductRestServiceImpl implements org.ecommerce.service.api.ProductRestService {
    @Inject
    ProductLogic productLogic;

    @Inject
    ProductMapper prodMapper;

    @Override
    public Response getAllProducts() {
        List<Product> products = productLogic.getAllProducts();
        List<ProductDto> response = prodMapper.toProductDtoList(products);
        return Response.ok(response).build();

    }

    @Override
    public Response saveProduct(Product product) {
        Product savedProduct = productLogic.saveProduct(product);
        return Response.ok(savedProduct).build();
    }

    @Override
    public Response updateProduct(int id,Product product) {
//        Product updatedProduct = productLogic.updateProduct(id,product);
        Product updatedProduct = productLogic.sendUpdatedProductToKafka(id,product);
        return Response.ok(updatedProduct).build();
    }

    @Override
    public Response updateProductQuantity(int id) {
        String response = productLogic.updateQuantity(id);
        return Response.ok(response).build();
    }

    @Override
    public Response deleteProduct(int id) {
        String response = productLogic.deleteProduct(id);
        return Response.ok(response).build();
    }

    @Override
    public Response getProductsByCategory(String category) {
        List<Product> filteredProducts = productLogic.getProductsByCategory(category);
        List<ProductDto> filteredProductsDto = prodMapper.toProductDtoList(filteredProducts);
        return Response.ok(filteredProductsDto).build();
    }

    @Override
    public Response getAllCategories() {
        List<String> categories = productLogic.getAllCategories();
        return Response.ok(categories).build();
    }
}
