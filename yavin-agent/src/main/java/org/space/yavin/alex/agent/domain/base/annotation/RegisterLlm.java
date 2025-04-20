package org.space.yavin.alex.agent.domain.base.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */

@Service
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterLlm {

    @AliasFor(annotation = Service.class, attribute = "value")
    String name();
}
