package org.space.yavin.alex.agent.thirdapi.common.response;

import cn.hutool.http.Method;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.infrastructure.utils.SnakeJsonUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@AllArgsConstructor
public class ApiResponseSpec {
    private static final Logger logger = LogManager.getLogger(ApiResponseSpec.class);


    private String apiName;
    private String url;
    private Object body;
    private Method method;
    private Map<String, String> headers;
    private Function<Long, WebClient.ResponseSpec> responseSupplier;
    private ApplicationEventPublisher eventPublisher;


    private <T> Flux<T> retrieveSSE(Long timeout, Function<String, T> converter) {
        WebClient.ResponseSpec response = responseSupplier.apply(timeout);
        ArrayList<String> sseResponse = new ArrayList<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        return response.bodyToFlux(String.class)
                .contextCapture()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(sseResponse::add)
                .map(converter)
                .doOnError(error::set);
    }

    private <T> Flux<T> retrieveSSE(Long timeout, TypeReference<T> typeReference) {
        return retrieveSSE(timeout, strRes -> {
            try {
                return SnakeJsonUtil.fromJsonStr(strRes, typeReference);
            } catch (IOException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <T> Flux<T> retrieveSSE(Long timeout, Class<T> typeClass) {
        return retrieveSSE(timeout, strRes -> {
            try {
                return SnakeJsonUtil.fromJsonStr(strRes, typeClass);
            } catch (IOException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <T> Flux<T> retrieveSSE(Long timeout) {
        TypeReference<T> typeReference = new TypeReference<>() {
        };
        return retrieveSSE(timeout, typeReference);
    }
}
