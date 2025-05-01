package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

public class AssistantMessage<T> extends Message<T> {

    @JsonCreator
    public AssistantMessage(T content) {
        super(RoleEnum.ASSISTANT, content);
    }
}
