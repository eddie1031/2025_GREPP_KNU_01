package io.eddie.ecommerce.domain.products.model.dto;

public record UpsertProductRequest(
        String name,
        String description,
        Long price
) {
}
