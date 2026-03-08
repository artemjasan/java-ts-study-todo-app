package com.dev.backend.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.backend.application.dtos.CategoryBaseOutputDTO;
import com.dev.backend.application.dtos.CategoryBaseInputDTO;
import com.dev.backend.application.dtos.CategoryUpdateInputDTO;
import com.dev.backend.application.exceptions.ApplicationException;
import com.dev.backend.application.exceptions.CategoryNotFoundException;
import com.dev.backend.application.interactors.CategoryCreateInteractor;
import com.dev.backend.application.interactors.CategoryGetInteractor;
import com.dev.backend.application.interactors.CategoryUpdateInteractor;
import com.dev.backend.application.interactors.CategoryGetAllInteractor;



@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryGetInteractor categoryGetInteractor;
    private final CategoryGetAllInteractor categoryGetAllInteractor;
    private final CategoryCreateInteractor categoryPostInteractor;
    private final CategoryUpdateInteractor categoryUpdateInteractor;

    public CategoryController(CategoryGetInteractor categoryGetInteractor,
                              CategoryGetAllInteractor categoryGetAllInteractor,
                              CategoryCreateInteractor categoryPostInteractor,
                              CategoryUpdateInteractor categoryUpdateInteractor
    ) {
        this.categoryGetInteractor = categoryGetInteractor;
        this.categoryGetAllInteractor = categoryGetAllInteractor;
        this.categoryPostInteractor = categoryPostInteractor;
        this.categoryUpdateInteractor = categoryUpdateInteractor;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryBaseOutputDTO>> getAllCategories() {
        List<CategoryBaseOutputDTO> categories = categoryGetAllInteractor.execute(null);
        return ResponseEntity.ok(categories);
    }
    

    @GetMapping("/{name}")
    public ResponseEntity<CategoryBaseOutputDTO> getCategoryByName(@PathVariable String name) throws CategoryNotFoundException, ApplicationException {
        CategoryBaseOutputDTO outputDTO = categoryGetInteractor.execute(name);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryBaseOutputDTO> getCategory(@PathVariable UUID uuid) throws CategoryNotFoundException, ApplicationException {
        CategoryBaseOutputDTO outputDTO = categoryGetInteractor.execute(uuid);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CategoryBaseOutputDTO> updateCategory(@PathVariable UUID uuid, @RequestBody CategoryBaseInputDTO inputDTO) throws CategoryNotFoundException {
        CategoryBaseOutputDTO outputDTO = categoryUpdateInteractor.execute(new CategoryUpdateInputDTO(uuid, inputDTO.getName()));
        return ResponseEntity.ok(outputDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryBaseOutputDTO> createCategory(@RequestBody CategoryBaseInputDTO inputDTO) {
        CategoryBaseOutputDTO outputDTO = categoryPostInteractor.execute(inputDTO);
        return ResponseEntity.ok(outputDTO);
    }
}