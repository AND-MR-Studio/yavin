package org.space.yavin.alex.agent.thirdapi.llm.response.kimi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;

@Getter
@NoArgsConstructor
public class KimiChoice {
    private Integer index;
    private TextMessage message;
    private String finishReason;
}