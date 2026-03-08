package com.dev.backend.domain.entities;

import java.util.UUID;

import com.dev.backend.domain.exceptions.InvalidNameArgumentException;

public interface Category {
    UUID getUuid();
    String getName();

    void setUuid(UUID uuid);
    void setName(String name) throws InvalidNameArgumentException;
}