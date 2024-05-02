package com.otters.computerstore.component.warrantyBill;

import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class WarrantyBillEntity extends NoteEntity {
    private String staffId;
    private String customerId;

    private Date warrantyDate;

    public WarrantyBillEntity(String note, String staffId, String customerId, Date warrantyDate) {
        super(note);
        this.staffId = staffId;
        this.customerId = customerId;
        this.warrantyDate = warrantyDate;
    }

    @Override
    public String toString() {
        return "WarrantyBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", warrantyDate=" + warrantyDate +
                '}';
    }
}
