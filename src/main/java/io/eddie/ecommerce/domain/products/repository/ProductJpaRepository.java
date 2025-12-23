package io.eddie.ecommerce.domain.products.repository;

import io.eddie.ecommerce.domain.products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

}
