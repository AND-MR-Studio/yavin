package org.space.yavin.alex.agent.infrastructure.exception.base;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public class InputRequiredException extends BaseException {

    public InputRequiredException(String code, String defaultMessage, Object... args) {
        super("input required", code, defaultMessage, args);
    }
}
