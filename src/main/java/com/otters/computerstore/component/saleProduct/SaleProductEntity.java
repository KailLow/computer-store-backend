package com.otters.computerstore.component.saleProduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.saleBill.SaleBillEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class SaleProductEntity extends NoteEntity {
    private Integer quantity;
    private String seri;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties(value = {"saleProducts"})
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "saleBill_id")
    @JsonIgnoreProperties(value = {"saleProducts"})
    private SaleBillEntity saleBill;


}