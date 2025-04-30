package org.space.yavin.alex.agent.domain.base.entity.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.ContentType;

/**
 * @author yyHuangfu
 * @create 2025/4/30
 */

@Getter
@AllArgsConstructor
public abstract class MessageContent {
    private final ContentType type;
}
