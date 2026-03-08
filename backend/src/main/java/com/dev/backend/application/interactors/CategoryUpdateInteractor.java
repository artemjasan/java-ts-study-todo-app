package com.dev.backend.application.interactors;

import org.springframework.stereotype.Service;

import com.dev.backend.application.common.CategoryGateway;
import com.dev.backend.application.dtos.CategoryBaseOutputDTO;
import com.dev.backend.application.dtos.CategoryUpdateInputDTO;
import com.dev.backend.application.exceptions.CategoryNotFoundException;
import com.dev.backend.domain.entities.Category;
import com.dev.backend.domain.entities.CategoryImpl;

@Service
public class CategoryUpdateInteractor implements Interactor<CategoryUpdateInputDTO, CategoryBaseOutputDTO> {
    final CategoryGateway gateway;

    public CategoryUpdateInteractor(CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public CategoryBaseOutputDTO execute(CategoryUpdateInputDTO data) throws CategoryNotFoundException {
        if (!gateway.existsByUuid(data.getUuid())) {
            throw new CategoryNotFoundException("Category not found");
        }
        Category category = gateway.updateCategory(new CategoryImpl(data.getUuid(), data.getName()));
        return new CategoryBaseOutputDTO(category.getUuid(), category.getName());
    }
}
