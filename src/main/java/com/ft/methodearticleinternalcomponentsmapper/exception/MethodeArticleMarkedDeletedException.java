package com.ft.methodearticleinternalcomponentsmapper.exception;

import java.util.UUID;

public class MethodeArticleMarkedDeletedException extends RuntimeException {

    private String type;

    public MethodeArticleMarkedDeletedException(UUID uuid, String type) {
        super(String.format("Story has been marked as deleted %s", uuid));
    }

    public String getType() {
        return type;
    }
}
