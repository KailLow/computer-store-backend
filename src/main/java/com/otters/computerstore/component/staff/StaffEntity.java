package com.otters.computerstore.component.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.component.permissionAssign.PermissionAssignEntity;
import com.otters.computerstore.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Builder
@Getter
@Setter
@AllArgsConstructor
public class StaffEntity extends BaseEntity implements UserDetails {
    private String name;
    private String phone;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String citizenId;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PermissionAssignEntity> permissionAssigns;
    public StaffEntity() {}

    public StaffEntity(String name, String phone, String password, String email, String citizenId) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.citizenId = citizenId;
    }

    public StaffEntity(String name, String phone, String password, String email, String citizenId, Role role) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.citizenId = citizenId;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", citizenId='" + citizenId + '\'' +
                '}';
    }
}
