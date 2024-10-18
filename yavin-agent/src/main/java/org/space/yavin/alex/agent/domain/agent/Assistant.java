package org.space.yavin.alex.agent.domain.agent;

import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.Message;
import org.space.yavin.alex.agent.domain.llm.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public class Assistant extends Agent {
    public Assistant(List<BaseTool> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    Flux<Message> _run(List<Message> messages) {
        return null;
    }

    private List<Message> prependKnowledgePrompt(List<Message> messages) {
        // assistant _prepend_knowledge_prompt
        return null;
    }
}
