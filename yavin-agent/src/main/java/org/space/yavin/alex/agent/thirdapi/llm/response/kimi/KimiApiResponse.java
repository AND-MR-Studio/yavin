package org.space.yavin.alex.agent.thirdapi.llm.response.kimi;

import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.thirdapi.common.response.LlmApiResponse;
import org.w3c.dom.Text;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2025/4/5
 */

@Getter
public class KimiApiResponse extends LlmApiResponse {
    private List<KimiChoice> choices;
    private KimiUsage usage;

    @Getter
    public static class KimiChoice {
        private Integer index;
        private TextMessage message;
        private String finishReason;
    }

    @Getter
    public static class KimiUsage {
        private Integer promptTokens;
        private Integer completionTokens;
        private Integer totalTokens;
    }
}
