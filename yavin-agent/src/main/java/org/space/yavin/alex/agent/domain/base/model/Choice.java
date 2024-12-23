package org.space.yavin.alex.agent.domain.base.model;

import lombok.Data;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Data
public class Choice {
    private String finishReason;
    private Message message;
}
