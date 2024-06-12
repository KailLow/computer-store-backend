package com.otters.computerstore.component.permission.dto;

import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.component.permission.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPermission {
    private boolean view = false;
    private boolean update = false;
    private boolean delete = false;

    public ItemPermission(List<PermissionEntity> permissions) {
        this.view = permissions.stream().anyMatch(permission -> permission.getPermissionType() == PermissionType.VIEW_ITEM || permission.getPermissionType() == PermissionType.VIEW_ALL);
        this.update = permissions.stream().anyMatch(permission -> permission.getPermissionType() == PermissionType.UPDATE_ITEM || permission.getPermissionType() == PermissionType.UPDATE_ALL);
        this.delete = permissions.stream().anyMatch(permission -> permission.getPermissionType() == PermissionType.DELETE_ITEM || permission.getPermissionType() == PermissionType.DELETE_ALL);
    }

}
