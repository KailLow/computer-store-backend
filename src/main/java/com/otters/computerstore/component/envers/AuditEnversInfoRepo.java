package com.otters.computerstore.component.envers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditEnversInfoRepo extends JpaRepository<AuditEnversInfo, Integer> {
    Optional<AuditEnversInfo> findById(int id);
    List<AuditEnversInfo> findByUsername(String username);

}