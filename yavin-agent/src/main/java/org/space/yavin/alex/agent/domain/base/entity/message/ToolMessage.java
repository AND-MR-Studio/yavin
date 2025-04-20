package org.space.yavin.alex.agent.domain.base.entity.message;

import org.space.yavin.alex.agent.domain.base.model.FunctionCall;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/1/5 20:29
 * @Description: ToDo
 */
public class ToolMessage extends Message {

    private FunctionCall functionCall;

    public ToolMessage(String role, FunctionCall functionCall) {
        super(role, null);
        this.functionCall = functionCall;
    }

    public ToolMessage(String role, String name, FunctionCall functionCall) {
        super(role, name);
        this.functionCall = functionCall;
    }

    @Override
    public Object getContent() {
        return functionCall;
    }

}
