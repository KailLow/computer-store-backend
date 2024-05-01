package com.otters.computerstore.component.supplier;

import com.otters.computerstore.entity.BaseEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class SupplierEntity extends NoteEntity {
    private String name;
    private String phone;
    private String address;
    private String email;

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
