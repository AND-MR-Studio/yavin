package org.space.yavin.alex.agent.domain.flux;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author yyHuangfu
 * @create 2025/1/26
 */
public class FromListToFlux {

    public static void main(String[] args) {
        Flux<String> flux = Flux.just("a", "b", "c");

        flux.subscribe(System.out::println);
    }

}
