package com.otters.computerstore.component.permission.dto;

import com.otters.computerstore.component.permission.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StaffPermissionResponse {
    private List<PermissionEntity> permissions;
    private List<GrantedAuthority> authorities;
}