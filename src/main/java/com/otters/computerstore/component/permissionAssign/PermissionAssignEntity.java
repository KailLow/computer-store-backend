package com.otters.computerstore.component.permissionAssign;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class PermissionAssignEntity {
    private String staffId;
    private String permissionId;
    private String itemId;
    private String type;

    public PermissionAssignEntity(String staffId, String permissionId, String itemId, String type) {
        this.staffId = staffId;
        this.permissionId = permissionId;
        this.itemId = itemId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PermissionAssignEntity{" +
                "staffId='" + staffId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
