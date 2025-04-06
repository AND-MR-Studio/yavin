package org.space.yavin.alex.agent.thirdapi.llm.response.kimi;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class KimiUsage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}