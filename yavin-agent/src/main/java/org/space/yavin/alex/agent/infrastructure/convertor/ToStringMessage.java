package org.space.yavin.alex.agent.infrastructure.convertor;

import org.space.yavin.alex.agent.domain.base.entity.content.TextContent;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;

/**
 * @author yyHuangfu
 * @create 2025/5/1
 */
public class ToStringMessage {

    public static <F> Message<String> convert(Message<F> message) {
        F content = message.getContent();
        if (content instanceof String text) {
            return new TextMessage(message.getRole(), text);
        } else if (content instanceof TextContent textContent) {
            return new TextMessage(message.getRole(), textContent.getText());
        }
        return null;
    }
}
