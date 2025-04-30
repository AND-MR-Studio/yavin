package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

import java.util.List;

import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.ASSISTANT;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */
@Slf4j
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextMessage.class, name = "text"),
        @JsonSubTypes.Type(value = MultimodMessage.class, name = "multimod")
})
@Getter
@AllArgsConstructor
public abstract class Message<T> {

    private final RoleEnum role;
    @Nullable
    private final String name;

    private final T content;

    public static Message ofAssistant(Object content) {
        if (content instanceof String) {
            return new TextMessage(ASSISTANT, (String) content);
        } else if (content instanceof List<?> &&
                !((List<?>) content).isEmpty() &&
                ((List<?>) content).get(0) instanceof ContentItem) {

            return new MultimodMessage(ASSISTANT, (List<ContentItem>) content);
        }
        return new TextMessage(ASSISTANT, ""); // todo 兜底回复
    }


}
