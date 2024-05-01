package com.otters.computerstore.component.saleBill;

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
public class SaleBillEntity extends NoteEntity {
    private String staffId;
    private String customerId;

    private String paymentMethod;
    private Date saleDate;
    private Float discount;

    public SaleBillEntity(String note, String staffId, String customerId, String paymentMethod, Date saleDate, Float discount) {
        super(note);
        this.staffId = staffId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.saleDate = saleDate;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "SaleBillEntity{" +
                "staffId='" + staffId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", saleDate=" + saleDate +
                ", discount=" + discount +
                '}';
    }
}
