package org.space.yavin.alex.agent.thirdapi.common.entity;

import cn.hutool.http.Method;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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
}
