package com.otters.computerstore.component.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.entity.BaseEntity;
import com.otters.computerstore.utils.validator.PhoneNumberFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@Audited
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StaffEntity extends BaseEntity implements UserDetails {
    @Pattern(regexp = "^[a-z0-9A-Z_àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ ]*$", message = "Name should not contain special characters")
    @NotNull
    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @Size(min = 1, message = "Password must be at least 1 characters long")
    private String password;
    @Column(unique = true)
    @Email(message = "Invalid email address format")
    private String email;
    @Column(unique = true)
    @Pattern(regexp = "^(\\d{9}|\\d{12})$", message = "Citizen ID must be 9 or 12 digits")
    private String citizenId;
    private String photoURL;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotAudited
    private Date lastOnline = null;
    @NotAudited
    @JsonIgnore
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties(value = {"staff"})
    private List<PermissionEntity> permissions = new ArrayList<>();

    public StaffEntity() {}

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
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(permissions.stream().map(permissionEntity -> new SimpleGrantedAuthority(permissionEntity.toString())).toList());
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
