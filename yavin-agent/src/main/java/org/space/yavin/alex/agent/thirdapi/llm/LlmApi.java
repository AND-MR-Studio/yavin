package org.space.yavin.alex.agent.thirdapi.llm;

import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.thirdapi.llm.response.GenerationResponse;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public interface LlmApi {

    Flux<GenerationResponse> call(
            String model,
            Object prompt,
            List<Map<String, String>> history,
            String apiKey,
            List<Message> messages,
            Object plugins,
            String workspace,
            Map<String, Object> addInfo
    );

}
