package org.space.yavin.alex.agent.config;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.space.yavin.alex.agent.config.model.ApiConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 配置属性类，用于绑定 rpc.api-config 前缀的配置项
 * todo 这里加@Getter会有问题，预计和@Bean有冲突，待详细了解@config机制
 *
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rpc.api-config")
public class ApiProperties {

    /**
     * 通义千问API配置（配置项前缀：rpc.api-config.qwen-chat）
     */
    private ApiConfig qwenChat;
}