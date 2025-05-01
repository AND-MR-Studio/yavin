package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.entity.content.Content;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
public class SystemMessage<T> extends Message<T> {

    @JsonCreator
    public SystemMessage(T content) {
        super(RoleEnum.SYSTEM, content);
    }
}
