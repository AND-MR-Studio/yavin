package org.space.yavin.alex.agent.thirdapi.llm.response.kimi;

import lombok.Getter;
import org.space.yavin.alex.agent.thirdapi.common.response.LlmApiResponse;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2025/4/5
 */

@Getter
public class KimiApiResponse extends LlmApiResponse {
    private List<KimiChoice> choices;
    private KimiUsage usage;
}
