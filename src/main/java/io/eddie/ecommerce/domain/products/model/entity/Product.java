package io.eddie.ecommerce.domain.products.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Product(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }



}
