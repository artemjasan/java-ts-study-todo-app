package com.dev.backend.application.interactors;

import com.dev.backend.domain.entities.Category;
import com.dev.backend.domain.entities.CategoryImpl;

import org.springframework.stereotype.Service;

import com.dev.backend.application.common.CategoryGateway;
import com.dev.backend.application.common.UUIDGenerator;
import com.dev.backend.application.dtos.CategoryBaseOutputDTO;
import com.dev.backend.application.dtos.CategoryBaseInputDTO;


@Service
public class CategoryCreateInteractor implements Interactor<CategoryBaseInputDTO, CategoryBaseOutputDTO> {
    final CategoryGateway gateway;
    final UUIDGenerator uuidGenerator;

    public CategoryCreateInteractor(CategoryGateway gateway, UUIDGenerator uuidGenerator) {
        this.gateway = gateway;
        this.uuidGenerator = uuidGenerator;
    }

    @Override
    public CategoryBaseOutputDTO execute(CategoryBaseInputDTO data) throws IllegalArgumentException {
        String name = data.getName();

        if (gateway.existsByName(name)) {
            throw new IllegalArgumentException("Category already exists");
        }
        Category category = new CategoryImpl(uuidGenerator.generateUUID(), name);
        gateway.saveCategory(category);

        return new CategoryBaseOutputDTO(category.getUuid(), category.getName());
    }
}
