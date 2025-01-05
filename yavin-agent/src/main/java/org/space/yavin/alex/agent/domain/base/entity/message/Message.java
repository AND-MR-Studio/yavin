package org.space.yavin.alex.agent.domain.base.entity.message;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;

import java.util.List;

import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.ASSISTANT;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private static final Logger logger = LogManager.getLogger(Message.class);

    private String role;
    @Nullable
    private String name;


    public Object getContent() {
        logger.warn("get the Parent Message Class, return empty Str content");
        return "";
    }

    public static Message ofAssistant(Object content) {
        if (content instanceof String) {
            return new TextMessage(ASSISTANT, (String) content);
        } else if (content instanceof List<?> &&
                !((List<?>) content).isEmpty() &&
                ((List<?>) content).getFirst() instanceof ContentItem) {

            return new MultimodMessage(ASSISTANT, (List<ContentItem>) content);
        }
        return new TextMessage(ASSISTANT, ""); // todo 兜底回复
    }


}
