package org.ecommerce.domain.repository;

import org.ecommerce.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.ws.rs.core.Response;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
