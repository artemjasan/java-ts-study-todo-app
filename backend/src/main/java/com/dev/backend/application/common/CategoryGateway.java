package com.dev.backend.application.common;

import java.util.List;
import java.util.Optional;

import java.util.UUID;
import com.dev.backend.domain.entities.Category;

public interface CategoryGateway {
    public boolean existsByName(String name);
    public boolean existsByUuid(UUID uuid);
    public Optional<Category> findCategoryByName(String name);
    public Optional<Category> findCategoryByUuid(UUID uuid);
    public List<Category> findAllCategories();
    public void saveCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(String name);
}