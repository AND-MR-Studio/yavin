package org.space.yavin.alex.agent.config.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Setter
@Getter
public class ApiConfig {
    private String uri;
    private Long timeout = 1000L;
}
