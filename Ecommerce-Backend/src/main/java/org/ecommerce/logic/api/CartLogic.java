package org.ecommerce.logic.api;

import org.ecommerce.domain.model.Cart;

import javax.ws.rs.core.Response;

public interface CartLogic {
    Cart getCart();

    String addEntry(int id);

    String removeEntry(int id);

    void removeCartEntry(int id);

    void editCartEntryCount(int id, int count);

//    int getSize();
}
