package io.eddie.ecommerce.domain.carts.model.entity;

import io.eddie.ecommerce.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class CartItem extends BaseEntity {

    private String sellerCode;

    private String productCode;
    private String productName;

    private Long productPrice;

    private Integer quantity;

}
