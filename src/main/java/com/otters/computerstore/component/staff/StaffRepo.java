package com.otters.computerstore.component.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity, UUID> {
    Optional<StaffEntity> findByEmail(String email);
}
