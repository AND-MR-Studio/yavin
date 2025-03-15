package org.space.yavin.alex.agent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yyHuangfu
 * @create 2025/2/1
 */

@Configuration
public class ReactiveConfig {


    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}
