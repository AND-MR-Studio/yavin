package org.space.yavin.alex.agent.infrastructure.exception.base;


import cn.hutool.core.util.StrUtil;
import org.space.yavin.alex.agent.infrastructure.utils.MessageUtil;

/**
 * 基础异常
 *
 * @author ruoyi
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, String code, String defaultMessage, Object[] args) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, null, args);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, defaultMessage, null);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, null, args);
    }

    public BaseException(String defaultMessage) {
        this(null, null, defaultMessage, null);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StrUtil.isEmpty(code)) {
            message = MessageUtil.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" + "module='" + module + '\'' + ", message='" + getMessage() + '\'' + '}';
    }
}
