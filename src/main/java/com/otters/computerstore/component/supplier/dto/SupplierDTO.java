package com.otters.computerstore.component.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String name;
    private String phone;
    private String email;
    private String address;

    public SupplierDTO(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }



    private String note;
}
