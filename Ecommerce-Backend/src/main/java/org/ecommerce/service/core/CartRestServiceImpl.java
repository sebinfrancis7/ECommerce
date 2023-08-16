package org.ecommerce.service.core;

import org.ecommerce.domain.model.Cart;
import org.ecommerce.domain.model.Product;
import org.ecommerce.logic.api.CartLogic;
import org.ecommerce.service.api.CartRestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
    public class CartRestServiceImpl implements CartRestService {
//
    @Inject
    CartLogic cartLogic;

    @Override
    public Response getCart() {
        Cart c = cartLogic.getCart();
        return Response.ok(c).build();
    }

    @Override
    public Response addCartEntry(int id) {
        String response = cartLogic.addEntry(id);
        String newResponse = "{\"response\": \"Product added\"}";
        return Response.ok(newResponse).build();
    }

    @Override
    public Response removeCartItem(int id) {
        String response = cartLogic.removeEntry(id);
        String newResponse = "{\"response\": \"Product removed\"}";
        return Response.ok(newResponse).build();
    }

    @Override
    public Response removeCartEntry(int id) {
        cartLogic.removeCartEntry(id);
        String newResponse = "{\"response\": \"Product removed\"}";
        return Response.ok(newResponse).build();
    }

    @Override
    public Response editCartEntryCount(int id, int count) {
        cartLogic.editCartEntryCount(id,count);
        String newResponse = "{\"response\": \"cart edited\"}";
        return Response.ok(newResponse).build();
    }

}