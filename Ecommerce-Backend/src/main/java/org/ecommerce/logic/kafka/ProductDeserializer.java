package org.ecommerce.logic.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.ecommerce.domain.model.Product;

public class ProductDeserializer extends ObjectMapperDeserializer<Product> {
    public ProductDeserializer(){
        super(Product.class);
    }
}
