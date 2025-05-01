package org.space.yavin.alex.agent.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/5/1
 */
@Getter
@AllArgsConstructor
public class DialogRequest {
    private List<Message<?>> messages;
    private Map<String, String> placeholders;
}
