package org.ecommerce.domain.repository;

import org.ecommerce.domain.model.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<CartEntry,Integer> {
}
