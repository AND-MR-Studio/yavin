package org.space.yavin.alex.agent.domain.llm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.domain.llm.base.BaseFnCallModel;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterLlm;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.model.Choice;
import org.space.yavin.alex.agent.thirdapi.llm.QwenChatApi;
import org.space.yavin.alex.agent.thirdapi.llm.response.GenerationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.llm.QwenChatAtDs.QWEN_CHAT;

/**
 * qwen dashscope
 *
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Slf4j
@RegisterLlm(name = QWEN_CHAT)
@AllArgsConstructor
public class QwenChatAtDs extends BaseFnCallModel {
    protected static final String QWEN_CHAT = "qwen-chat";
    private QwenChatApi qwenChatApi;

    @Override
    protected Flux<List<Message>> chatStream(List<Message> messages, Map<String, Object> cfg) {
        // todo
        Flux<GenerationResponse> response = qwenChatApi.call(
                QWEN_CHAT,
                null,
                null,
                messages,
                cfg
        );
        return response.map(llmRsp -> {
            Choice firstChoice = llmRsp.getOutput().getChoices().get(0);
            return List.of(Message.ofAssistant(firstChoice.getMessage().getContent()));
        });
    }

    @Override
    protected Mono<List<Message>> chatNoStream(List<Message> messages, Map<String, Object> cfg) {
        return Mono.empty();
    }

    @Override
    protected Flux<List<Message>> chatWithFunction(List<Message> messages, List<Map<String, String>> functions, boolean stream, Map<String, Object> cfg) {
        return null;
    }

}
