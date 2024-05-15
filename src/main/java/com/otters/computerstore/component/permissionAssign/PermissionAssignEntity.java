package com.otters.computerstore.component.permissionAssign;

import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class PermissionAssignEntity extends NoteEntity {

    private String itemId;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private StaffEntity staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private PermissionEntity permission;

    public PermissionAssignEntity(String note, String itemId, String type) {
        super(note);
        this.itemId = itemId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PermissionAssignEntity{" +
                ", itemId='" + itemId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
