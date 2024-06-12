package com.otters.computerstore.component.importBill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.importProduct.ImportProductEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class ImportBillEntity extends NoteEntity {
    private String staffId;
    private String supplierId;
    private String paymentMethod;

    public ImportBillEntity(String staffId, String supplierId, String paymentMethod) {
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
    }

    public ImportBillEntity(String id, String staffId, String supplierId, String paymentMethod) {
        this.setId(id);
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
    }
    @JsonIgnoreProperties(value = {"import_bill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "importBill")
    private List<ImportProductEntity> importProducts;
}