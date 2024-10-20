package org.space.yavin.alex.agent.domain.memory;

import org.space.yavin.alex.agent.domain.agent.Agent;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.Message;
import org.space.yavin.alex.agent.domain.llm.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.infrastructure.Settings.*;

/**
 * Memory is special agent for file management.
 *
 * @author yyHuangfu
 * @create 2024/10/20
 */
public class Memory extends Agent {

    private final Map<String, Object> config;

    public Memory(List<BaseTool> tools, BaseChatModel llm, String systemMessage,
                  List<String> files, Map<String, Object> ragConfig) {
        // 初始化配置
        Map<String, Object> cfg = ragConfig != null ? ragConfig : new HashMap<>();
        Integer maxRefToken = (Integer) cfg.getOrDefault("max_ref_token", DEFAULT_MAX_REF_TOKEN);
        Integer parserPageSize = (Integer) cfg.getOrDefault("parser_page_size", DEFAULT_PARSER_PAGE_SIZE);
        List<String> ragSearchers = (List<String>) cfg.getOrDefault("rag_searchers", DEFAULT_RAG_SEARCHERS);
        String ragKeygenStrategy = (String) cfg.getOrDefault("rag_keygen_strategy", DEFAULT_RAG_KEYGEN_STRATEGY);
        BaseTool retrieval = new BaseTool("re");

        super(tools, llm, systemMessage, name, description);
    }

    @Override
    protected Flux<Message> _run(List<Message> messages, Map<String, Object> addInfo) {
        return null;
    }
}
