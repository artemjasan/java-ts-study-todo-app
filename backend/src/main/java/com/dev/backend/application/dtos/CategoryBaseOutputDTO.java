package com.dev.backend.application.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBaseOutputDTO {
    private UUID uuid;
    private String name;
}
