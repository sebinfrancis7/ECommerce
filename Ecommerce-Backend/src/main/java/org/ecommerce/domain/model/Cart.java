package org.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private int id;
    @OneToMany()
    @JoinColumn(name = "cart_id_fk")
    private List<CartEntry> cartItems;
    private int total;
    private int size;
    public void addToCart(CartEntry ce){
        cartItems.add(ce);
    }

    public void removeCartEntry(CartEntry ce){
        cartItems.remove(ce);
    }
    public void updateCartSize(int value){
        this.size += value;
    }
//    public void removeFromCart(Product p){
//        cartItems.remove(p);
//    }
}
