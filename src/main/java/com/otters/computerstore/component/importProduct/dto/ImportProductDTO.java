package com.otters.computerstore.component.importProduct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ImportProductDTO {
    private String productId;
    private Integer quantity;
    private Long price;

    public ImportProductDTO(String productId, Integer quantity, Long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

}