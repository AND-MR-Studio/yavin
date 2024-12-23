package org.space.yavin.alex.agent.domain.base.entity.message;

import lombok.Data;
import lombok.Setter;
import org.space.yavin.alex.agent.domain.base.model.FunctionCall;

/**
 * @author yyHuangfu
 * @create 2024/12/22
 */

@Data
public class TextMessage extends Message {

    @Setter
    private String content;

    public TextMessage(String role, String content) {
        super(role, null, null);
        this.content = content;
    }

    public TextMessage(String role, String content, String name,
                       FunctionCall functionCall) {
        super(role, name, functionCall);
        this.content = content;
    }
}
