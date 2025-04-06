package org.space.yavin.alex.agent.thirdapi.llm.response;

import lombok.Getter;
import org.space.yavin.alex.agent.thirdapi.common.response.LlmApiResponse;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/4/5
 */

@Getter
public class KimiApiResponse extends LlmApiResponse {
    private List<Map<String, Object>> choices;
    private Map<String, Object> usage;
}
