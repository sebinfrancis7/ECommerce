package org.ecommerce.logic.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.ecommerce.domain.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProductProducer {
    @Inject
    @Channel("products-output")
    Emitter<Product> productEmitter;

    public void sendUpdatedProductToKafka(Product product){
        productEmitter.send(product);
//        System.out.println("Sent to kafka");
    }
}
