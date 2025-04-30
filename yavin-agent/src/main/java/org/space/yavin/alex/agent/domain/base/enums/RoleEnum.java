package org.space.yavin.alex.agent.domain.base.enums;

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

    private final String role;
}
