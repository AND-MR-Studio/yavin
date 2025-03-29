package org.space.yavin.alex.agent.thirdapi.llm;

import org.mockito.Mockito;
import org.space.yavin.alex.agent.config.ApiProperties;
import org.space.yavin.alex.agent.config.entity.ApiConfig;
import org.space.yavin.alex.agent.thirdapi.ApiUtil;
import org.space.yavin.alex.agent.thirdapi.common.response.ApiResponseSpec;
import org.space.yavin.alex.agent.thirdapi.llm.response.GenerationResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Kimi Chat API测试配置类
 * 提供测试所需的模拟组件
 *
 * @author yyHuangfu
 */
@TestConfiguration
public class KimiChatApiTestConfig {

    /**
     * 提供ApiConfig Bean，用于KimiChatApi
     */
    @Bean
    @Primary
    public ApiConfig kimiChatApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setUrl("https://api.moonshot.cn/v1/chat/completions");
        apiConfig.setTimeout(5000L);
        return apiConfig;
    }
    
    /**
     * 提供ApiProperties Bean，用于KimiChatApi
     */
    @Bean
    @Primary
    public ApiProperties apiProperties() {
        ApiProperties properties = new ApiProperties();
        
        // 设置KimiChat配置
        ApiConfig kimiConfig = new ApiConfig();
        kimiConfig.setUrl("https://api.moonshot.cn/v1/chat/completions");
        kimiConfig.setTimeout(5000L);
        properties.setKimiChat(kimiConfig);
        
        // 设置API Key
        ApiProperties.ApiKeyConfig apiKeyConfig = new ApiProperties.ApiKeyConfig();
        apiKeyConfig.setKimi("sk-0TsvAhmvcwF8W0oLc5TTq5kPrHx91zQum9S2nONTiLkSqlg3");
        properties.setApiKey(apiKeyConfig);
        
        return properties;
    }
}