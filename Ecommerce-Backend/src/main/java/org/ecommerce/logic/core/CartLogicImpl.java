package org.ecommerce.logic.core;

import org.ecommerce.domain.model.Cart;
import org.ecommerce.domain.model.CartEntry;
import org.ecommerce.domain.model.Product;
import org.ecommerce.domain.repository.CartEntryRepository;
import org.ecommerce.domain.repository.CartRepository;
import org.ecommerce.domain.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class CartLogicImpl implements org.ecommerce.logic.api.CartLogic {
    @Inject
    ProductRepository prodRepo;

    @Inject
    CartRepository cartRepo;

    @Inject
    CartEntryRepository cartEntryRepo;


    @Override
    public Cart getCart() {
        return cartRepo.findById(1).get();
    }

    @Override
    public String addEntry(int id) {
        Cart c = cartRepo.findById(1).get();
        Product p = prodRepo.findById(id).get();
        p.setQuantity(p.getQuantity() - 1);
        prodRepo.save(p);
        CartEntry newCartEntry = null;

        boolean flag = false;
        for(CartEntry ce: c.getCartItems()){
            if(ce.getProduct().getId() == id){
                flag = true;
                newCartEntry = ce;
                newCartEntry.setCount(ce.getCount() + 1);
            }
        }
        if(!flag){
            newCartEntry = new CartEntry(p,1);
        }
        cartEntryRepo.save(newCartEntry);
        c.addToCart(newCartEntry);
        c.updateCartSize(1);
        int previousTotal = c.getTotal();
        int newTotal = (int) (previousTotal + p.getPrice());
        c.setTotal(newTotal);
        cartRepo.save(c);
        return "product added to cart";
    }

    @Override
    public String removeEntry(int id) {
        Cart c = cartRepo.findById(1).get();
        Product p = prodRepo.findById(id).get();
        p.setQuantity(p.getQuantity() + 1);
        prodRepo.save(p);
        boolean flag = false; // to avoid concurrent modification exception
        CartEntry toRemove = null;
        for(CartEntry ce: c.getCartItems()){
            if(ce.getProduct().getId() == id){
                if(ce.getCount() == 1){
                    flag = true;
                    toRemove = ce;
//                    cartEntryRepo.delete(ce);
//                    c.removeCartEntry(ce);
                }else{
                    CartEntry newCartEntry = ce;
                    newCartEntry.setCount(ce.getCount() - 1);
                    cartEntryRepo.save(newCartEntry);
                }
            }
        }
        if(flag){
            c.removeCartEntry(toRemove);
        }
        int previousTotal = c.getTotal();
        int newTotal = (int) (previousTotal - p.getPrice());
        c.setTotal(newTotal);
        c.setSize(c.getSize() - 1);
        cartRepo.save(c);
        if(flag){
            cartEntryRepo.deleteById(toRemove.getId());
        }
        return "product removed from cart";
    }

    @Override
    public void removeCartEntry(int id) {
        Cart cart = cartRepo.findById(1).get();
        CartEntry cartEntry = cartEntryRepo.findById(id).get();
        Product product = cartEntry.getProduct();
        product.setQuantity(product.getQuantity() + cartEntry.getCount());
        prodRepo.save(product);
        cart.removeCartEntry(cartEntryRepo.findById(id).get());

        int previousTotal = cart.getTotal();
        int newTotal = (int) (previousTotal - (cartEntry.getCount() * product.getPrice()));
        cart.setTotal(newTotal);
        cart.setSize(cart.getSize() - cartEntry.getCount());
        cartRepo.save(cart);
        cartEntryRepo.deleteById(id);
    }

    @Override
    public void editCartEntryCount(int id, int count) {
        Cart cart = cartRepo.findById(1).get();
        CartEntry cartEntry = cartEntryRepo.findById(id).get();
        Product product = prodRepo.findById(cartEntry.getProduct().getId()).get();

        int totalProductQuantity = product.getQuantity() + cartEntry.getCount();
        product.setQuantity(totalProductQuantity - count);
        cartEntry.setCount(count);

        prodRepo.save(product);
        cartEntryRepo.save(cartEntry);

        int newSize = cart.getCartItems().stream().map(ci -> ci.getCount()).reduce(0,(subtotal,element) -> subtotal + element);
        Double newTotal =cart.getCartItems().stream().map(ci -> (ci.getProduct().getPrice() * ci.getCount())).reduce(Double.valueOf(0),(subTotal, element) -> subTotal+element);
        int total = newTotal.intValue();
        cart.setTotal(total);
        cart.setSize(newSize);
        cartRepo.save(cart);
    }
}
