package com.otters.computerstore.component.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);

    List<CategoryEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);

    Optional<CategoryEntity> findById(String id);
}
