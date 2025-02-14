package com.rest.assured.util;

import java.util.UUID;

public class GenerateId {
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
