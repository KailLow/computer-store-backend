package com.otters.computerstore.component.warrantyProduct;

import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class WarrantyProductEntity extends BaseEntity {
    private String staffId;
    private String warrantyBillId;
    private String productId;
    private Integer quantity;
    private String warrantyContent;

    public WarrantyProductEntity(String staffId, String warrantyBillId, String productId, Integer quantity, String warrantyContent) {
        this.staffId = staffId;
        this.warrantyBillId = warrantyBillId;
        this.productId = productId;
        this.quantity = quantity;
        this.warrantyContent = warrantyContent;
    }

    @Override
    public String toString() {
        return "WarrantyProductEntity{" +
                "staffId='" + staffId + '\'' +
                ", warrantyBillId='" + warrantyBillId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", warrantyContent='" + warrantyContent + '\'' +
                '}';
    }
}
