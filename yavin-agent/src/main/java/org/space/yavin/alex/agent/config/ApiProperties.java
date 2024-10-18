package org.space.yavin.alex.agent.config;

import lombok.Getter;
import lombok.Setter;
import org.space.yavin.alex.agent.config.model.ApiConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rpc.api-config")
public class ApiProperties {
    private ApiConfig qwenChat;
}
