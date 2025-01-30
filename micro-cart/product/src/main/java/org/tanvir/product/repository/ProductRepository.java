package org.tanvir.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tanvir.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
}
