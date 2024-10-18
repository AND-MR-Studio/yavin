package org.space.yavin.alex.agent.thirdapi.common.entity;

import lombok.Data;
import org.space.yavin.alex.agent.domain.base.model.Choice;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
@Data
public class GenerationOutput {
    private String text;
    private List<Choice> choices;
    private String finishReason;
}
