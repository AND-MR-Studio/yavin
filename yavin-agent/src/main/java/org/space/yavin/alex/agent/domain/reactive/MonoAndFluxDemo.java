package org.space.yavin.alex.agent.domain.reactive;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author yyHuangfu
 * @create 2025/1/26
 */
public class MonoAndFluxDemo {

    public static void main(String[] args) {
        reshapeRequestDemo();
    }


    private static void subscribeDemo() {
        // 1. 创建一个publisher,同时Flux具备流式处理能力
        Flux<String> flux = Flux.just("a", "b", "c");
        // subscribe时，也会生成lambda subscriber,进行订阅消费
        flux.map(String::toUpperCase).subscribe(
                System.out::println,
                e -> System.err.println("error: " + e),
                () -> System.out.println("Done"));
    }

    private static void disposeDemo() {
        // subscribe返回Disposable类型，可以用来取消订阅
        // 2. Cancelling a subscribe() with Its Disposable
        Flux<String> flux2 = Flux.just("a", "b", "c");
        // LambdaSubscriber#dispose()
        flux2.subscribe(System.out::println).dispose();
    }

    private static void baseSubscribeDemo() {
        // 3. An Alternative to Lambdas: BaseSubscriber
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(ss);
    }

    private static void reshapeRequestDemo() {
        // 4. On Backpressure and Ways to Reshape Requests
        Flux.range(1, 10)
                // r是请求上游的数据
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new SampleSubscriber<>());
    }

    public static class SampleSubscriber<T> extends BaseSubscriber<T> {
        // 在编程中，“hook”（钩子）指的是一种扩展点或回调机制，
        // 允许开发者在不修改原有代码的情况下，将自定义逻辑插入到框架或库的特定执行流程中。
        // Reactor 的设计哲学：通过钩子方法简化响应式编程的复杂性，让开发者专注于业务逻辑，而非底层流控制。
        @Override
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }


}

