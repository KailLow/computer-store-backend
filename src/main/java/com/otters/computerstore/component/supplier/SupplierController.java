package com.otters.computerstore.component.supplier;
import com.otters.computerstore.component.permission.EntityType;
import com.otters.computerstore.component.permission.PermissionEntity;
import com.otters.computerstore.component.permission.PermissionRepo;
import com.otters.computerstore.component.permission.dto.ItemPermission;
import com.otters.computerstore.component.staff.Role;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.supplier.dto.SupplierDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService service;
    private final PermissionRepo permissionRepo;

    @GetMapping
    public List<SupplierEntity> getAll(@RequestParam(defaultValue = "") String name) {
        return service.findByName(name);
    }
    @GetMapping("termination")
    public List<SupplierEntity> getAllTermination(@RequestParam(defaultValue = "") String name) {
        return service.findTermination(name);
    }
    @GetMapping("{id}")
    public SupplierEntity getOne(@PathVariable String id) {
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid SupplierDTO supplierDTO) {
        return ResponseEntity.ok(service.add(supplierDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@Valid SupplierDTO supplierDTO, @PathVariable String id) {
        return ResponseEntity.ok(service.update(supplierDTO, id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.remove(id);
    }

    @GetMapping("permission/{id}")
    public ResponseEntity<?> listPermission(Principal connectedUser, @PathVariable String id) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (staff.getRole() == Role.ADMIN) return ResponseEntity.ok(new ItemPermission(true, true, true));
        List<PermissionEntity> permissions = permissionRepo.findByEntityTypeAndEntityIdAndStaffId(EntityType.PRODUCT, id, staff.getId());
        return ResponseEntity.ok(new ItemPermission(permissions));
    }
    @GetMapping("history/{id}")
    public List<?> getALlHistory(@PathVariable String id) {
        return service.getRevisions(id);
    }
}
