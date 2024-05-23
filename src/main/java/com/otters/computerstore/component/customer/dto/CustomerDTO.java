package com.otters.computerstore.component.customer.dto;

import com.otters.computerstore.component.saleBill.dto.SaleBillDTO;
import com.otters.computerstore.component.warrantyBill.dto.WarrantyBillDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private String phone;
    private String address;
    private List<WarrantyBillDTO> warrantyBills;
    private List<SaleBillDTO> saleBills;


    public CustomerDTO(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


}