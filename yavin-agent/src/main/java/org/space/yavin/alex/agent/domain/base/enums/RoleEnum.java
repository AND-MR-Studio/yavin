package org.space.yavin.alex.agent.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yyHuangfu
 * @create 2025/4/29
 */

@Getter
@AllArgsConstructor
public enum RoleEnum {

    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function");

    @JsonValue
    private final String role;
}
