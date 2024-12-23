package org.space.yavin.alex.agent.domain.base.entity.content;

import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE("image"),
    FILE("file");

    private final String key;

    ContentType(String key) {
        this.key = key;
    }

}
