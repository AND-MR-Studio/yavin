package org.space.yavin.alex.agent.domain.llm;

import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterLlm;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.model.Choice;
import org.space.yavin.alex.agent.thirdapi.llm.QwenChatApi;
import org.space.yavin.alex.agent.thirdapi.llm.response.LlmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.llm.QwenChatAtDs.QWEN_CHAT;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Slf4j
@RegisterLlm(name = QWEN_CHAT)
public class QwenChatAtDs extends BaseFnCallModel {
    protected static final String QWEN_CHAT = "qwen-chat";
    @Autowired
    private QwenChatApi qwenChatApi;

    @Override
    protected Flux<Message> _chatStream(List<Message> messages, Map<String, Object> cfg) {
        // todo
        Flux<LlmResponse> response = qwenChatApi.call(
                QWEN_CHAT,
                null,
                null,
                (String) cfg.get("apiKey"),
                messages,
                null,
                null,
                cfg
        );
        return response.map(llmRsp -> {
            Choice firstChoice = llmRsp.getOutput().getChoices().get(0);
            return Message.ofAssistant(firstChoice.getMessage().getContent());
        });
    }

    @Override
    protected List<Message> _chatNoStream() {
        return List.of();
    }

    @Override
    protected Flux<Message> _chatWithFunction() {
        return null;
    }
}
