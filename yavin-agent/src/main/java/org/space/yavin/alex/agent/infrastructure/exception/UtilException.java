package org.space.yavin.alex.agent.infrastructure.exception;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public class UtilException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UtilException(String message)
    {
        super(message);
    }

    public UtilException(Throwable cause)
    {
        super(cause);
    }

    public UtilException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
