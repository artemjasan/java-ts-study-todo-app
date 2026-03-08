package com.dev.backend.domain.entities;

import com.dev.backend.domain.exceptions.InvalidNameArgumentException;

import java.util.UUID;

public class CategoryImpl implements Category {
    private UUID uuid;
    private String name;

    public CategoryImpl(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setName(String name) throws InvalidNameArgumentException {
        if (name == null || name.isBlank()) {
            throw new InvalidNameArgumentException("Category name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
