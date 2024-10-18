package org.space.yavin.alex.agent.domain.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Data
@AllArgsConstructor
public class Message {
    private String role;
    private String content;
    private String name;
    private FunctionCall functionCall;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    Message(String role, String content, String name) {
        this.role = role;
        this.content = content;
        this.name = name;
    }

}
