package org.space.yavin.alex.agent.thirdapi.llm.response;

import lombok.Getter;
import lombok.Setter;
import org.space.yavin.alex.agent.thirdapi.common.entity.GenerationOutput;
import org.space.yavin.alex.agent.thirdapi.common.entity.GenerationUsage;
import org.space.yavin.alex.agent.thirdapi.common.response.ApiResponse;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
public class GenerationResponse extends ApiResponse {
    private GenerationOutput output;
    private GenerationUsage usage;

}
