package com.otters.computerstore.component.permission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.entity.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@Entity
@Table
@RequiredArgsConstructor
public class PermissionEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    private String entityId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "staff_id")
    @JsonIgnoreProperties(value = {"permissions"})
    @NotNull
    private StaffEntity staff;

    @Override
    public String toString() {
        return permissionType + ":" + entityType + (entityId != null ? ":" + entityId : "");
    }
}