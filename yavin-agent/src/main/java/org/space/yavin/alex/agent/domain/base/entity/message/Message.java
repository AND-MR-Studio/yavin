package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.entity.content.Content;
import org.space.yavin.alex.agent.domain.base.entity.content.TextContent;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */
@Slf4j
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserMessage.class, name = "user"),
        @JsonSubTypes.Type(value = SystemMessage.class, name = "system"),
        @JsonSubTypes.Type(value = AssistantMessage.class, name = "assistant")
})
@Getter
@AllArgsConstructor
public abstract class Message<T> {

    private final RoleEnum role;

    @Nullable
    @Setter
    private String name;

    @Setter
    private T content;

    Message(final RoleEnum role, T content) {
        this.role = role;
        this.name = null;
        this.content = content;
    }

    public static UserMessage<Content> ofUser(String text) {
        return new UserMessage<>(new TextContent(text));
    }

    public static SystemMessage<Content> ofSystem(String text) {
        return new SystemMessage<>(new TextContent(text));
    }

    public static AssistantMessage<Content> ofAssistant(String text) {
        return new AssistantMessage<>(new TextContent(text));
    }

    public static AssistantMessage<Content> ofAssistant(Content content) {
        return new AssistantMessage<>(content);
    }

}
