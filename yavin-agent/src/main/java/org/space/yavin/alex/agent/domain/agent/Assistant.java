package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.util.StrUtil;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.Message;
import org.space.yavin.alex.agent.domain.llm.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public class Assistant extends Agent {
    public Assistant(List<BaseTool> tools, BaseChatModel llm, String systemMessage, String name, String description) {
        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> _run(List<Message> messages, Map<String, Object> addInfo) { // todo 语言先不加了
        return null;
    }

    private List<Message> prependKnowledgePrompt(List<Message> messages, Map<String, Object> addInfo) {
        // assistant _prepend_knowledge_prompt
        List<Message> newMsgs = new ArrayList<>(messages);
        String knowledge = (String) addInfo.get("knowledge");
        if (StrUtil.isNotBlank(knowledge)) {

        }

        return null;
    }
}
