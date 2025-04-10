package org.space.yavin.alex.agent.config;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.space.yavin.alex.agent.config.entity.ApiConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性类，用于绑定 rpc.api-config 前缀的配置项
 * todo 这里加@Getter会有问题，预计和@Bean有冲突，待详细了解@config机制
 *
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rpc.api-config")
public class ApiProperties {

    private ApiKeyConfig apiKey;

    /**
     * 通义千问API配置（配置项前缀：rpc.api-config.qwen-chat）
     */
    private ApiConfig qwenChat;
    private ApiConfig kimiChat;

    // 将内部类改为静态内部类: 非静态的内部类不能独立存在，需要通过外部类实例才能创建。
    @Setter
    public static class ApiKeyConfig {
        private static final String BEARER = "Bearer ";

        private String kimi;

        @NotNull
        public String getKimi() {
            // 修改点3：空值处理防止拼接异常
            return BEARER + kimi;
        }
    }
}