package org.space.yavin.alex.agent.domain.llm;

import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterLlm;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.thirdapi.llm.kimi.KimiApiResponse;
import org.space.yavin.alex.agent.thirdapi.llm.kimi.KimiChatApi;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.llm.KimiChatModel.KIMI_CHAT;


/**
 * @author yyHuangfu
 * @create 2025/3/29
 */

@Slf4j
@RegisterLlm(name = KIMI_CHAT)
public class DeepseekChatModel extends BaseChatModel {
    public static final String KIMI_CHAT = "kimi_chat";
    private final KimiChatApi chatApi;
    private final String model;

    DeepseekChatModel(KimiChatApi chatApi) {
        this.chatApi = chatApi;
        this.model = "kimi-latest";
    }

    @Override
    protected Flux<List<Message<?>>> chatStream(List<Message<?>> messages, Map<String, Object> cfg) {
        return null;
    }

    @Override
    protected Mono<List<Message<?>>> chatNoStream(List<Message<?>> messages, Map<String, Object> cfg) {
        // 显式声明泛型类型
        return chatApi.call(model, null, null, messages, null)
                .map(rsp -> rsp.getChoices().stream()
                        .map(KimiApiResponse.KimiChoice::getMessage)
                        .<Message<?>>map(msg -> msg) // 显式声明泛型类型
                        .toList())
                .flatMap(Flux::fromIterable)
                .collectList();
    }


    @Override
    protected Flux<List<Message<?>>> chatWithFunction(List<Message<?>> messages, List<Map<String, String>> functions, boolean stream, Map<String, Object> cfg) {
        return null;
    }
}
