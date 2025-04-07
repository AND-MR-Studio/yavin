package org.space.yavin.alex.agent.domain.agent.yishao;

import org.space.yavin.alex.agent.domain.agent.Agent;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:17
 * @Description: 一勺海龟汤小程序
 */
public class YiShaoAgent extends Agent {

    public YiShaoAgent(List<BaseTool<?>> tools, BaseChatModel llm, String name, String description) {
        super(tools, llm, "", name, description);
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        return null;
    }


}
