package org.space.yavin.alex.agent.domain.base.entity.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
public class ImageContent extends Content {
    private final String imageUrl;

    @JsonCreator
    public ImageContent(String imageUrl) {
        super(ContentType.IMAGE);
        this.imageUrl = imageUrl;
    }
}
