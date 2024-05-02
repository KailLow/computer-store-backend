package com.otters.computerstore.component.importBill;

import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class ImportBillEntity extends NoteEntity {
    private String staffId;
    private String supplierId;
    private String paymentMethod;
    private Date importDate;

    public ImportBillEntity(String note, String staffId, String supplierId, String paymentMethod, Date importDate) {
        super(note);
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "ImportBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", importDate=" + importDate +
                '}';
    }
}
