package org.space.yavin.alex.agent.config.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiConfig {
    private String url;
    private Long timeout = 1000L;
}
