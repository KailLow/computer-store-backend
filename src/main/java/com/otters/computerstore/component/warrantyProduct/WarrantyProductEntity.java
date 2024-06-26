package com.otters.computerstore.component.warrantyProduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.warrantyBill.WarrantyBillEntity;
import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;
@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class WarrantyProductEntity extends BaseEntity {
    private String staffId;
    private Integer quantity;
    private String warrantyContent;
    private String note;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    private ProductEntity product;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "warrantyBill_id")
    @JsonIgnoreProperties(value = {"warrantyProducts"})
    private WarrantyBillEntity warrantyBill;
}