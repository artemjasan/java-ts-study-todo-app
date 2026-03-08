package com.dev.backend.application.common;

import java.util.UUID;

public interface UUIDGenerator {
    UUID generateUUID();
    String generateUUIDString();
    UUID generateFromString(String uuidString);
}
