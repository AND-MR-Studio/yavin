package org.space.yavin.alex.agent.domain.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yyHuangfu
 * @create 2024/12/16
 */

@Getter
@AllArgsConstructor
public class TextContent extends ContentItem{
    private String text;

    public void setText(String text) {
        this.text = text;
    }
}
