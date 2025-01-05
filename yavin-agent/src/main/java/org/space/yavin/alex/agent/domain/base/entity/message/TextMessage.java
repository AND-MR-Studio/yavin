package org.space.yavin.alex.agent.domain.base.entity.message;

import lombok.Data;
import lombok.Setter;

/**
 * @author yyHuangfu
 * @create 2024/12/22
 * @description 文本Message
 */

@Data
public class TextMessage extends Message {

    @Setter
    private String content;

    public TextMessage(String role, String content) {
        super(role, null);
        this.content = content;
    }

    public TextMessage(String role, String content, String name) {
        super(role, name);
        this.content = content;
    }
}
