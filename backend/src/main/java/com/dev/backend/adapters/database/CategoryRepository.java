package com.dev.backend.adapters.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDB, UUID> {
    boolean existsByName(String name);
    boolean existsByUuid(UUID uuid);
    CategoryDB findByName(String name);
    CategoryDB findByUuid(UUID uuid);
}