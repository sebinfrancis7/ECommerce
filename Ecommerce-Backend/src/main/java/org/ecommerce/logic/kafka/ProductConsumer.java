package org.ecommerce.logic.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.ecommerce.domain.model.Product;
import org.ecommerce.domain.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProductConsumer {

    @Inject
    ProductRepository productRepository;
    @Incoming("products-input")
    @Transactional
    public void getUpdatedProduct(Product product){
        productRepository.save(product);
    }
}
