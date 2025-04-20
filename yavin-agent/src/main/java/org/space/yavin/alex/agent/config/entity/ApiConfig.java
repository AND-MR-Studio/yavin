package org.space.yavin.alex.agent.config.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class ApiConfig {
    private String url;
    private Long timeout = 1000L;
}
