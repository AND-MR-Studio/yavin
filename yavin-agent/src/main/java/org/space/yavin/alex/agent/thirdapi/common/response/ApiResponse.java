package org.space.yavin.alex.agent.thirdapi.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Args:
 * request_id (str): The request id.
 * status_code (int): HTTP status code, 200 indicates that the
 * request was successful, and others indicate an error。
 * code (str): Error code if error occurs, otherwise empty str.
 * message (str): Set to error message on error.
 * output (Any): The request output.
 * usage (Any): The request usage information.
 *
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
public abstract class ApiResponse {
    private Integer statusCode;
    private String requestId;
    private String code;
    private String message;
}
