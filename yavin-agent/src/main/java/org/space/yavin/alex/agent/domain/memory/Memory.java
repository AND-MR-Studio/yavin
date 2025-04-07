package org.space.yavin.alex.agent.domain.memory;

import org.space.yavin.alex.agent.domain.agent.Agent;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.tool.Retrieval;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.space.yavin.alex.agent.infrastructure.Settings.*;

/**
 * Memory is special agent for file management.
 *
 * @author yyHuangfu
 * @create 2024/10/20
 */
public class Memory extends Agent {

    private Map<String, Object> config;
    private Integer maxRefToken;
    private Integer parserPageSize;
    private List<String> ragSearchers;
    private String ragKeygenStrategy;


    public Memory(List<BaseTool<?>> tools, BaseChatModel llm, String systemMessage,
                  List<String> files, Map<String, Object> ragConfig) {

        super(tools, llm, systemMessage, Memory.class.getSimpleName(), "memory can use retrieval tool for RAG");

        // 参数校验
        if (tools == null || llm == null || systemMessage == null || files == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        // 初始化配置
        this.config = Optional.ofNullable(ragConfig).orElse(new HashMap<>());

        this.maxRefToken = Optional.ofNullable(this.config.get("max_ref_token"))
                .map(Integer.class::cast)
                .orElse(DEFAULT_MAX_REF_TOKEN);
        this.parserPageSize = Optional.ofNullable(this.config.get("parser_page_size"))
                .map(Integer.class::cast)
                .orElse(DEFAULT_PARSER_PAGE_SIZE);
        this.ragSearchers = Optional.ofNullable(this.config.get("rag_searchers"))
                .map(List.class::cast)
                .orElse(DEFAULT_RAG_SEARCHERS);
        this.ragKeygenStrategy = Optional.ofNullable(this.config.get("rag_keygen_strategy"))
                .map(String.class::cast)
                .orElse(DEFAULT_RAG_KEYGEN_STRATEGY);

        // 添加工具
        super.getTools().add(new Retrieval(config));
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        return null;
    }
}
