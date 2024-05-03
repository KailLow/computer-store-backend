package com.otters.computerstore.component.permission;

import com.otters.computerstore.component.permissionAssign.PermissionAssignEntity;
import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class PermissionEntity extends BaseEntity {
    private String name;
    private String phone;
    private String username;
    private String password;
    private String role;
    private String email;
    private String citizenId;

    @OneToMany(mappedBy = "permissions")
    private Set<PermissionAssignEntity> permissionAssigns;

    public PermissionEntity(String name, String phone, String username, String password, String role, String email, String citizenId) {
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.citizenId = citizenId;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", citizenId='" + citizenId + '\'' +
                '}';
    }
}
