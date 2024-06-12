package com.otters.computerstore.component.importProduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.importBill.ImportBillEntity;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Audited
public class ImportProductEntity extends BaseEntity {
    private Integer quantity;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"importProducts"})
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "importBill_id")
    @JsonIgnoreProperties(value = {"importProducts"})
    private ImportBillEntity importBill;

}
