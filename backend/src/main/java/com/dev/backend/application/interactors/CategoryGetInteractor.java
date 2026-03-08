package com.dev.backend.application.interactors;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dev.backend.application.common.CategoryGateway;
import com.dev.backend.application.dtos.CategoryBaseOutputDTO;
import com.dev.backend.application.exceptions.ApplicationException;
import com.dev.backend.application.exceptions.CategoryNotFoundException;
import com.dev.backend.domain.entities.Category;


@Service
public class CategoryGetInteractor<T> implements Interactor<T, CategoryBaseOutputDTO> {
    final CategoryGateway gateway;

    public CategoryGetInteractor(CategoryGateway gateway) {
        this.gateway = gateway;
    }

    public CategoryBaseOutputDTO execute(Object param) throws CategoryNotFoundException, ApplicationException {
        if (param instanceof String name) {
            Optional<Category> optionalCategory = gateway.findCategoryByName(name);
    
            Category category = optionalCategory.orElseThrow(() ->
                new CategoryNotFoundException("Category not found with name: " + name)
            );  
            return new CategoryBaseOutputDTO(category.getUuid(), category.getName());
        } else if (param instanceof UUID uuid) {
            Optional<Category> optionalCategory = gateway.findCategoryByUuid(uuid);
    
            Category category = optionalCategory.orElseThrow(() ->
                new CategoryNotFoundException("Category not found with uuid: " + uuid)
            );  
            return new CategoryBaseOutputDTO(category.getUuid(), category.getName());
        } else {
            throw new ApplicationException("Unsupported parameter type");
        }
    }
}

