package io.eddie.ecommerce.domain.products.model.entity;

import io.eddie.ecommerce.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Long price;

    @Builder
    public Product(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;

//        this.code = generateCode();

    }


    public void update(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;

//        this.updatedAt = LocalDateTime.now();
        updateClock();
    }

}
