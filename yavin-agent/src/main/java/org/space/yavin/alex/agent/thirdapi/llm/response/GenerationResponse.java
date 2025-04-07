package org.space.yavin.alex.agent.thirdapi.llm.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.space.yavin.alex.agent.domain.base.model.Choice;
import org.space.yavin.alex.agent.thirdapi.common.response.LlmApiResponse;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
public class GenerationResponse extends LlmApiResponse {
    private GenerationOutput output;
    private GenerationUsage usage;

    @Getter
    public static class GenerationOutput {
        private String text;
        private List<Choice> choices;
        private String finishReason;
    }

    @Getter
    public static class GenerationUsage {
        private Integer inputToken;
        private Integer outputToken;
    }


}
