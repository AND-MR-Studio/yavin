package org.space.yavin.alex.agent.domain.base.entity.content;

import java.util.HashMap;

public class ContentItem extends HashMap<String, Object> {

    public ContentItem(ContentType type, String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        put(type.getKey(), value);
    }

    public String getText() {
        return (String) get(ContentType.TEXT.getKey());
    }

    public String getImage() {
        return (String) get(ContentType.IMAGE.getKey());
    }

    public String getFile() {
        return (String) get(ContentType.FILE.getKey());
    }

    public ContentType getType() {
        if (size() != 1) {
            throw new IllegalStateException("ContentItem should contain exactly one entry.");
        }
        return ContentType.valueOf(keySet().iterator().next().toUpperCase());
    }

    public String getValue() {
        if (size() != 1) {
            throw new IllegalStateException("ContentItem should contain exactly one entry.");
        }
        return (String) values().iterator().next();
    }

    @Override
    public String toString() {
        return "ContentItem(" + entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((a, b) -> a + ", " + b)
                .orElse("") + ")";
    }

}
