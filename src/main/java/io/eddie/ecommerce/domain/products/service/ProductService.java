package io.eddie.ecommerce.domain.products.service;

import io.eddie.ecommerce.domain.products.model.dto.UpsertProductRequest;
import io.eddie.ecommerce.domain.products.model.entity.Product;
import io.eddie.ecommerce.domain.products.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;

    @Transactional
    public Product save(UpsertProductRequest request) {

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();

        return productJpaRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code) {

        Optional<Product> productOptional = productJpaRepository.findByCode(code);

        Product foundProduct = productOptional.orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );

        return foundProduct;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Transactional
    public Product update(String code, UpsertProductRequest request) {

        Optional<Product> productOptional = productJpaRepository.findByCode(code);

        Product foundProduct = productOptional.orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );

        foundProduct.update(
                request.name(),
                request.description(),
                request.price()
        );

        return foundProduct;

    }

    @Transactional
    public String delete(String targetCode) {

        Optional<Product> productOptional = productJpaRepository.findByCode(targetCode);

        Product foundProduct = productOptional.orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );

        productJpaRepository.delete(foundProduct);

        return foundProduct.getCode();

    }


}
