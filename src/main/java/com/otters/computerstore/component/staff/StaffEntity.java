package com.otters.computerstore.component.staff;

import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class StaffEntity extends BaseEntity {
    private String name;
    private String phone;
    private String password;
    private String role;
    private String email;
    private String citizenId;

    public StaffEntity() {}

    public StaffEntity(String name, String phone, String password, String email, String citizenId) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.citizenId = citizenId;
    }
}
