package org.space.yavin.alex.agent.domain.llm.base;


import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.DEFAULT_SYSTEM_MESSAGE;
import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.SYSTEM;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Getter
public abstract class BaseChatModel {

    /**
     * LLM 聊天接口。
     * <p>
     * 参数:
     * - messages: 输入的消息列表。
     * - functions: 输入的函数调用，支持 OpenAI 格式。
     * - stream: 是否使用流式生成。
     * - extra_generate_cfg: 额外的 LLM 生成超参数。
     * <p>
     * 返回:
     * 由 LLM 生成的消息列表响应。
     */
    public Flux<List<Message>> chat(List<Message> messages,
                                    List<Map<String, String>> functions,
                                    boolean stream,
                                    Map<String, Object> cfg) {
        List<Message> cloneMessages = new ArrayList<>(messages);
        if (!SYSTEM.equals(cloneMessages.getFirst().getRole())) {
            messages.addFirst(new TextMessage(SYSTEM, DEFAULT_SYSTEM_MESSAGE));
        }
        // 针对cfg参数做不同的逻辑处理
        boolean functionMode = isFunctionMode(functions);
        Flux<List<Message>> output;
        if (functionMode) {
            output = chatWithFunction(messages, functions, stream, cfg);
        } else {
            if (stream) {
                output = chatStream(messages, cfg);
            } else {
                output = chatNoStream(messages, cfg).flux();
            }
        }
        // output解析和校验
        return output;
    }

    protected abstract Flux<List<Message>> chatStream(List<Message> messages, Map<String, Object> cfg);

    protected abstract Mono<List<Message>> chatNoStream(List<Message> messages, Map<String, Object> cfg);

    protected abstract Flux<List<Message>> chatWithFunction(List<Message> messages, List<Map<String, String>> functions,
                                                            boolean stream, Map<String, Object> cfg);


    // ------------------ private -----------------------
    private static boolean isFunctionMode(List<Map<String, String>> functions) {
        return CollUtil.isNotEmpty(functions);
    }
}
