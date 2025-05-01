package org.space.yavin.alex.agent.thirdapi.llm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.space.yavin.alex.agent.config.ApiProperties;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.infrastructure.exception.base.InputRequiredException;
import org.space.yavin.alex.agent.thirdapi.ApiUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
@Component
@AllArgsConstructor
public class QwenChatApi implements LlmApi {

    private static final String QWEN_CHAT = "qwen-chat";

    private final ApiProperties apiProperties;

    private final ApiUtil apiUtil;

    @Override
    public Flux<GenerationResponse> call(
            String model,
            Object prompt,
            List<Message<?>> history,
            List<Message<?>> messages,
            Map<String, Object> addInfo
    ) {
        if ((prompt == null || prompt.toString().isEmpty()) && (messages == null || messages.isEmpty())) {
            throw new InputRequiredException(QWEN_CHAT, "prompt or messages is required");
        }
        if (model == null || model.isEmpty()) {
            throw new InputRequiredException(QWEN_CHAT, "Model is required!");
        }
        QwenChatRequest request = QwenChatRequest.of(model, prompt);
        return apiUtil.postJson(QWEN_CHAT, apiProperties.getQwenChat().getUrl(), null, request)
                .retrieveSSE(apiProperties.getQwenChat().getTimeout());
    }

    @Data
    @AllArgsConstructor
    static class QwenChatRequest {
        private String model;
        private Map<String, String> parameters;
        private Map<String, String> input;

        public static QwenChatRequest of(String model, Object prompt) {
            Map<String, String> inputMap = new HashMap<>();
            inputMap.put("prompt", prompt.toString());

            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("result_format", "message");
            return new QwenChatRequest(model, paramsMap, inputMap);
        }
    }

}


