package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.util.StrUtil;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.infrastructure.constant.AgentConstants.KNOWLEDGE;

/**
 * Assistant = Tools + FunctionCall
 *
 * @author yyHuangfu
 * @create 2024/10/18
 */
public class Assistant extends FnCallAgent {
    public Assistant(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message<?>> process(List<Message<?>> messages, Map<String, Object> addInfo) { // todo 语言先不加了
        // Q&A with RAG and tool use abilities.
        return null;
    }

    private List<Message<?>> prependKnowledgePrompt(List<Message<?>> messages, Map<String, Object> addInfo) {
        // assistant _prepend_knowledge_prompt
        List<Message<?>> newMsgs = new ArrayList<>(messages);
        String knowledge = (String) addInfo.get(KNOWLEDGE);
        if (StrUtil.isEmpty(knowledge)) {

        }
        if (StrUtil.isNotBlank(knowledge)) {

        }
        return null;
    }
}
