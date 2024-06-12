package com.otters.computerstore.component.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionEntity, String> {
    Optional<PermissionEntity> findByPermissionTypeAndEntityTypeAndEntityIdAndStaffId(String permissionType, String entityType, String entityId, String staffId);
    Optional<PermissionEntity> findById(String id);

    List<PermissionEntity> findByStaffIdAndIsStopped(String id, boolean isStopped);
    List<PermissionEntity> findByEntityTypeAndEntityIdAndStaffId(EntityType entityType, String entityId, String staffId);
}