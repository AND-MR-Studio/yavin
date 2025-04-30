package org.space.yavin.alex.agent.domain.base.entity.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
public class TextContent extends MessageContent {

    private final String text;

    @JsonCreator
    public TextContent(String text) {
        super(ContentType.TEXT);
        this.text = text;
    }
}
