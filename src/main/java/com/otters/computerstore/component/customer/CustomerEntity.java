package com.otters.computerstore.component.customer;

import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table
@Setter
public class CustomerEntity extends BaseEntity {
    private String name;
    private String phone;
    private String address;

    public CustomerEntity(){};

    public CustomerEntity(String name, String phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
