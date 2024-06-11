package com.otters.computerstore.component.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.saleBill.SaleBillEntity;
import com.otters.computerstore.component.warrantyBill.WarrantyBillEntity;
import com.otters.computerstore.entity.BaseEntity;
import com.otters.computerstore.utils.validator.PhoneNumberFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Audited
public class CustomerEntity extends BaseEntity {
    private String name;
    @Column(unique=true)
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    private String address;
    //sai ne
    @JsonIgnoreProperties(value = {"warrantyBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    @NotAudited
    @JsonIgnore
    private List<WarrantyBillEntity> warrantyBills;

    @JsonIgnoreProperties(value = {"saleBill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    @NotAudited
    @JsonIgnore
    private List<SaleBillEntity> saleBills;
}
