package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.space.yavin.alex.agent.domain.base.entity.content.MessageContent;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
public class SystemMessage extends Message<MessageContent> {

    @JsonCreator
    public SystemMessage(@Nullable String name, MessageContent content) {
        super(RoleEnum.SYSTEM, name, content);
    }
}
