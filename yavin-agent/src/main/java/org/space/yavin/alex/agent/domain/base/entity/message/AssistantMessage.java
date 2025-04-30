package org.space.yavin.alex.agent.domain.base.entity.message;

import org.jetbrains.annotations.Nullable;
import org.space.yavin.alex.agent.domain.base.entity.content.MessageContent;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */
public class AssistantMessage extends Message<MessageContent> {

    public AssistantMessage(@Nullable String name, MessageContent content) {
        super(RoleEnum.ASSISTANT, name, content);
    }
}
