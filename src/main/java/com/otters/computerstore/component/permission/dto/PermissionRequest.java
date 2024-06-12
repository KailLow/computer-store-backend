package com.otters.computerstore.component.permission.dto;

import com.otters.computerstore.component.permission.EntityType;
import com.otters.computerstore.component.permission.PermissionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PermissionRequest {
    private PermissionType permissionType;
    private EntityType entityType;
    private String entityId;
    private String staffId;
}