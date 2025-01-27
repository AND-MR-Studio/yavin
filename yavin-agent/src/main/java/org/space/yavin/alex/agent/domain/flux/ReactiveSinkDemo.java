package org.space.yavin.alex.agent.domain.flux;

import reactor.core.publisher.Flux;

/**
 * <a href="https://projectreactor.io/docs/core/release/reference/coreFeatures/programmatically-creating-sequence.html">
 * Project Reactor Documentation</a> *
 *
 * @author yyHuangfu
 * @create 2025/1/27
 */
public class ReactiveSinkDemo {
    // 在响应式编程中，“sink” 一词用于描述一个控制事件触发的接口，
    // 开发者通过它主动向数据流（Flux 或 Mono）中注入事件（如 onNext、onError、onComplete）

    public static void main(String[] args) {
        stateBasedGenerate().subscribe(System.out::println);
    }

    // 1. Synchronous generate
    private static Flux<String> stateBasedGenerate() {
        return Flux.generate(
                () -> 0,            // We supply the initial state value of 0.
                (state, sink) -> {
                    // We use the state to choose what to emit (a row in the multiplication table of 3).
                    sink.next("3 x " + state + " = " + 3 * state);
                    // We also use it to choose when to stop.
                    if (state == 10) sink.complete();
                    return state + 1;
                });
    }

    // 2. Asynchronous and Multi-threaded: create

}
