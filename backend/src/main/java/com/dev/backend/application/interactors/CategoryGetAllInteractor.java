package com.dev.backend.application.interactors;


import org.springframework.stereotype.Service;
import com.dev.backend.application.common.CategoryGateway;
import com.dev.backend.application.dtos.CategoryBaseOutputDTO;
import com.dev.backend.domain.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryGetAllInteractor implements Interactor<Void, List<CategoryBaseOutputDTO>> {
    private final CategoryGateway gateway;

    public CategoryGetAllInteractor(CategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<CategoryBaseOutputDTO> execute(Void data) {
        List<Category> categories =gateway.findAllCategories();
        return categories.stream()
            .map(category -> new CategoryBaseOutputDTO(category.getUuid(), category.getName()))
            .collect(Collectors.toList());
    }
}