package org.space.yavin.alex.agent.thirdapi;

import cn.hutool.http.Method;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.infrastructure.utils.SnakeJsonUtil;
import org.space.yavin.alex.agent.thirdapi.common.response.ApiResponseSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Component
public class ApiUtil {
    private static final Logger logger = LogManager.getLogger(ApiUtil.class);

    @Autowired
    private WebClient webClient;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public ApiResponseSpec get(String apiName, String url, Map<String, String> headers) {

        Function<Long, WebClient.ResponseSpec> supplier = null;
        try {
            URI uri = new URI(url);
            supplier = timeout -> webClient.get()
                    .uri(uri)
                    .httpRequest(request ->
                            ((HttpClientRequest) request.getNativeRequest())
                                    .responseTimeout(Duration.ofMillis(timeout)))
                    .headers(hs -> {
                        headers.forEach(hs::add);
                    })
                    .retrieve();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return new ApiResponseSpec(apiName, url, "", Method.GET, headers, supplier, eventPublisher);
    }

    public ApiResponseSpec postJson(String apiName, String url, Map<String, String> headers, Object body) {
        Map<String, Object> bodyMap = SnakeJsonUtil.fromObj(body, new TypeReference<Map<String, Object>>() {
        });

        Function<Long, WebClient.ResponseSpec> supplier = null;
        try {
            URI uri = new URI(url);
            supplier = timeout -> webClient.post()
                    .uri(uri)
                    .httpRequest(request ->
                            ((HttpClientRequest) request.getNativeRequest())
                                    .responseTimeout(Duration.ofMillis(timeout)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(hs -> headers.forEach(hs::add))
                    .bodyValue(bodyMap)
                    .retrieve();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return new ApiResponseSpec(apiName, url, body, Method.POST, headers, supplier, eventPublisher);

    }
}
