package io.eddie.ecommerce.domain.products.controller;

import io.eddie.ecommerce.domain.products.model.dto.UpsertProductRequest;
import io.eddie.ecommerce.domain.products.model.entity.Product;
import io.eddie.ecommerce.domain.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(
            @RequestBody UpsertProductRequest request
    ) {
        Product saved = productService.save(request);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(saved);
    }

    @GetMapping
    public ResponseEntity getAllProduct() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getProductByCode(
            @PathVariable String productCode
    ) {
        Product foundProduct = productService.findByCode(productCode);

        return ResponseEntity.ok(foundProduct);
    }

    @PatchMapping("/{productCode}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String productCode,
            @RequestBody UpsertProductRequest request
    ) {
        Product updatedProduct = productService.update(productCode, request);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<String> deleteProductByCode(
            @PathVariable String productCode
    ) {
        String deletedProductCode = productService.delete(productCode);
        return ResponseEntity
                .ok(deletedProductCode);
    }


}
