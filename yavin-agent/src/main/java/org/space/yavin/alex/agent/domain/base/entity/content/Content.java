package org.space.yavin.alex.agent.domain.base.entity.content;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContent.class, name = "text"),
        @JsonSubTypes.Type(value = ImageContent.class, name = "image"),
})
@Getter
@AllArgsConstructor
public abstract class Content {
    private final ContentType type;

    public static TextContent ofText(String text) {
        return new TextContent(text);
    }

    public static ImageContent ofImage(String imageUrl) {
        return new ImageContent(imageUrl);
    }
}
