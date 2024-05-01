package com.otters.computerstore.component.product;

import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    private String name;
    private Long price;
    private Integer quantity;
    private Date warrantyPeriod;
    private Boolean isAvailable;

    public ProductEntity(String name, Long price, Integer quantity, Date warrantyPeriod, Boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", warrantyPeriod=" + warrantyPeriod +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
