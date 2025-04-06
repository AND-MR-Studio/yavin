package org.space.yavin.alex.agent.thirdapi.llm;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.config.ApiProperties;
import org.space.yavin.alex.agent.config.entity.ApiConfig;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.infrastructure.exception.base.InputRequiredException;
import org.space.yavin.alex.agent.thirdapi.ApiUtil;
import org.space.yavin.alex.agent.thirdapi.llm.response.kimi.KimiApiResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author yyHuangfu
 * @create 2025/3/29
 */
@Slf4j
@Component
@AllArgsConstructor
public class KimiChatApi implements LlmApi {

    private static final String KIMI_CHAT = "kimi-chat";
    private final ApiProperties apiProperties;
    private final ApiUtil apiUtil;

    @Override
    public Flux<KimiApiResponse> call(String model, Object prompt, List<Message> history,
                                      List<Message> messages, Object plugins, String workspace,
                                      Map<String, Object> addInfo) {
        if (model == null || model.isEmpty()) {
            throw new InputRequiredException(KIMI_CHAT, "Model is required!");
        }
        if (CollUtil.isEmpty(messages)) {
            log.info("message is empty, get null response");
            return Flux.empty();
        }
        ApiConfig kimiChat = apiProperties.getKimiChat();
        Map<String, String> kimiReqHeader = Map.of(
                AUTHORIZATION, apiProperties.getApiKey().getKimi()
        );

        KimiChatRequest req = KimiChatRequest.of(model, messages, history);
        return apiUtil.postJson(KIMI_CHAT, kimiChat.getUrl(), kimiReqHeader, req)
                .retrieveSSE(kimiChat.getTimeout(), KimiApiResponse.class);
    }

    @Data
    @AllArgsConstructor
    static class KimiChatRequest {
        private String model;
        private List<Message> messages;

        public static KimiChatRequest of(String model, List<Message> messages, List<Message> history) {
            // 这里reqMessages必须是TextMessage的子类型
            List<Message> reqMessages = new ArrayList<>();
            // 先添加历史记录
            if (history != null) {
                reqMessages.addAll(history); // 假设Message是TextMessage的子类型
            }
            // 再添加当前消息
            if (messages != null) {
                reqMessages.addAll(messages);
            }
            return new KimiChatRequest(model, reqMessages);
        }
    }
}
