package com.otters.computerstore.component.supplier;

import com.otters.computerstore.entity.BaseEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class SupplierEntity extends NoteEntity {
    private String name;
    private String phone;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SupplierEntity(String note, String name, String phone, String email, String address) {
        super(note);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
