package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
    private final String name;

    private final T content;

}
