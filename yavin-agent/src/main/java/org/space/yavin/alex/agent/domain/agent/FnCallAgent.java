package org.space.yavin.alex.agent.domain.agent;

import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.infrastructure.Settings.MAX_LLM_CALL_PER_RUN;

/**
 * This is a widely applicable function call agent
 * integrated with llm and tool use ability.
 *
 * @author yyHuangfu
 * @create 2024/10/22
 */
public class FnCallAgent extends Agent {
    public FnCallAgent(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        List<Message> msg = new ArrayList<>(messages);
        int numLlmCallAvailable = MAX_LLM_CALL_PER_RUN;
        while (numLlmCallAvailable > 0) {
            Flux<List<Message>> rsp = super.callLlm(messages, addInfo);
            numLlmCallAvailable--;
        }

        return null;
    }
}
