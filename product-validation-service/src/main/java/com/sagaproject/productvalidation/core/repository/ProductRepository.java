package com.sagaproject.productvalidation.core.repository;

import com.sagaproject.productvalidation.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByCode(String code);
}
