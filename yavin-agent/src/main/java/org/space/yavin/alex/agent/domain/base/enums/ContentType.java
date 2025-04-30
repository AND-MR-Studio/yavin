package org.space.yavin.alex.agent.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE("image"),
    FILE("file");

    @JsonValue
    private final String key;

    ContentType(String key) {
        this.key = key;
    }

}
