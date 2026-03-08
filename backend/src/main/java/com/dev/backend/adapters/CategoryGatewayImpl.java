package com.dev.backend.adapters;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.dev.backend.adapters.database.CategoryDB;
import com.dev.backend.adapters.database.CategoryRepository;
import com.dev.backend.application.common.CategoryGateway;
import com.dev.backend.domain.entities.Category;
import com.dev.backend.domain.entities.CategoryImpl;


@Service
public class CategoryGatewayImpl implements CategoryGateway {
    private final CategoryRepository categoryRepository;

    public CategoryGatewayImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private Optional<Category> getCategoryFromCategoryDB(CategoryDB categoryDB) {
        return categoryDB != null ? 
               Optional.of(new CategoryImpl(categoryDB.getUuid(), categoryDB.getName())) : 
               Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {
        CategoryDB categoryDB = new CategoryDB(category.getUuid(), category.getName());
        categoryRepository.save(categoryDB);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return categoryRepository.existsByUuid(uuid);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        CategoryDB categoryDB = categoryRepository.findByName(name);
        return getCategoryFromCategoryDB(categoryDB);
    }

    @Override
    public Optional<Category> findCategoryByUuid(UUID uuid) {
        CategoryDB categoryDB = categoryRepository.findByUuid(uuid);
        return getCategoryFromCategoryDB(categoryDB);
    }


    @Override
    public Category updateCategory(Category category) {
        categoryRepository.save(new CategoryDB(category.getUuid(), category.getName()));
        return category;
    }

    @Override
    public void deleteCategory(String name) {
        CategoryDB category = categoryRepository.findByName(name);
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findAllCategories() {
        List<CategoryDB> categoriesDB = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();

        categoriesDB.forEach(categoryDB -> 
            categories.add(new CategoryImpl(categoryDB.getUuid(), categoryDB.getName()))
        );
        return categories;
    }
}