package org.space.yavin.alex.agent.domain.base.entity.content;

import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
public class ImageContent extends MessageContent {
    private final String imageUrl;

    public ImageContent(String imageUrl) {
        super(ContentType.IMAGE);
        this.imageUrl = imageUrl;
    }
}
