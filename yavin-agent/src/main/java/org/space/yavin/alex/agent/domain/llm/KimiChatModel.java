package org.space.yavin.alex.agent.domain.llm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterLlm;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.thirdapi.llm.KimiChatApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.llm.KimiChatModel.KIMI_CHAT;

/**
 * @author yyHuangfu
 * @create 2025/3/29
 */

@Slf4j
@RegisterLlm(name = KIMI_CHAT)
@Service
@AllArgsConstructor
public class KimiChatModel extends BaseChatModel {
    protected static final String KIMI_CHAT = "kimi_chat";
    private KimiChatApi chatApi;
    private String model;

    @Override
    protected Flux<List<Message>> chatStream(List<Message> messages, Map<String, Object> cfg) {
        return null;
    }

    @Override
    protected List<Message> chatNoStream() {
        chatApi.call(
                model,
        )
    }

    @Override
    protected Flux<List<Message>> chatWithFunction() {
        return null;
    }
}
