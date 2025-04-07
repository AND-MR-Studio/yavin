package org.space.yavin.alex.agent.domain.agent;

import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * The most basic form of an agent is just a LLM,
 * not augmented with any tool or workflow.
 *
 * 历史对话处理：
 *
 *
 * @Author : Alex Huangfu
 * @Date: 2025/3/15 15:32
 * @Description: ToDo
 */
@Slf4j
public class BasicAgent extends Agent {

    public BasicAgent(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        return callLlm(messages, addInfo).onErrorResume(e -> {
            log.error("Error processing message: {}", e.getMessage());
            return Flux.empty();
        }).concatMapIterable(list -> list); // iterable就是for循环，把list中的元素一个一个取出来
    }
}
