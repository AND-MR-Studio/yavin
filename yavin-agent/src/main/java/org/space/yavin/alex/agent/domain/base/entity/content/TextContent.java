package org.space.yavin.alex.agent.domain.base.entity.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Setter
@Getter
public class TextContent extends Content {

    private String text;

    @JsonCreator
    public TextContent(String text) {
        super(ContentType.TEXT);
        this.text = text;
    }
}
