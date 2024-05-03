package com.otters.computerstore.component.permissionAssign;

import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class PermissionAssignEntity extends NoteEntity {
    private String staffId;
    private String permissionId;
    private String itemId;
    private String type;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private PermissionEntity permissions;

    public PermissionAssignEntity(String note, String staffId, String permissionId, String itemId, String type) {
        super(note);
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
