package com.otters.computerstore.component.product;

import com.otters.computerstore.component.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findById(String id);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
    List<ProductEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);

    List<ProductEntity> findByNameContainingIgnoreCaseAndCategoryAndIsStopped(String name, CategoryEntity category, boolean isStopped);
}
