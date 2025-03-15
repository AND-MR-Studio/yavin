package org.space.yavin.alex.agent.domain.agent;

import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * The most basic form of an agent is just a LLM,
 * not augmented with any tool or workflow.
 *
 * @Author : Alex Huangfu
 * @Date: 2025/3/15 15:32
 * @Description: ToDo
 */

public class BasicAgent extends Agent {

    public BasicAgent(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        return callLlm(messages, addInfo);
    }
}
