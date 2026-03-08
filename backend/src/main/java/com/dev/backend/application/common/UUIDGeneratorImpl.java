package com.dev.backend.application.common;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDGeneratorImpl implements UUIDGenerator {
    @Override
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    @Override
    public String generateUUIDString() {
        return generateUUID().toString();
    }

    @Override
    public UUID generateFromString(String uuidString) {
        return UUID.fromString(uuidString);
    }
}