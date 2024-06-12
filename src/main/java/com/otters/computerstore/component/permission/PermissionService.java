package com.otters.computerstore.component.permission;

import com.otters.computerstore.component.permission.dto.PermissionRequest;
import com.otters.computerstore.component.permission.dto.StaffPermissionResponse;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.staff.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepo permissionRepo;
    private final StaffRepo staffRepository;

    public StaffPermissionResponse getStaffPermission(String staffId) {
        StaffEntity staff = staffRepository.findById(staffId).get();
        return new StaffPermissionResponse(permissionRepo.findByStaffIdAndIsStopped(staffId, false), (List<GrantedAuthority>) staff.getAuthorities());
    }

    public PermissionEntity add(PermissionRequest permissionRequest, String staffId) {
        Optional<StaffEntity> staff = staffRepository.findById(staffId);

        PermissionEntity permission = new PermissionEntity();
        permission.setPermissionType(permissionRequest.getPermissionType());
        permission.setEntityType(permissionRequest.getEntityType());
        permission.setEntityId(permissionRequest.getEntityId());
        staff.ifPresent(permission::setStaff);

        return permissionRepo.save(permission);
    }

    public void remove(String permissionId) {
//        Optional<PermissionEntity> permission = permissionRepo.findById(permissionId);
//        permission.ifPresent(permissionRepo::delete);
//        permissionRepo.findById(permissionId);
        permissionRepo.deleteById(permissionId);
        PermissionEntity permission = permissionRepo.findById(permissionId).get();
        permission.setIsStopped(true);
        permissionRepo.save(permission);
    }
}