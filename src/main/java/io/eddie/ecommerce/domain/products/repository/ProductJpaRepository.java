package io.eddie.ecommerce.domain.products.repository;

import io.eddie.ecommerce.domain.products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
