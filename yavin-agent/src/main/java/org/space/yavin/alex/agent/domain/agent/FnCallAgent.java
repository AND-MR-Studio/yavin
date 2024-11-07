package org.space.yavin.alex.agent.domain.agent;

import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/22
 */
public class FnCallAgent extends Agent{
    public FnCallAgent(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> _run(List<Message> messages, Map<String, Object> addInfo) {
        return null;
    }
}
