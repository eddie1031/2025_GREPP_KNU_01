package io.eddie.ecommerce.domain.carts.model.entity;

import io.eddie.ecommerce.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Cart extends BaseEntity {

    private String accountCode;

}

