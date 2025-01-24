package org.space.yavin.alex.agent.domain.flux;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author yyHuangfu
 * @create 2025/1/22
 */
public class ReactiveStreamDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. 定义一个发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // 2. 定义一个订阅者
        Flow.Subscriber<String> subscriber = new MySubscriber();

        // 4. 绑定,可以绑定多个
        publisher.subscribe(subscriber);
        for (int i = 0; i < 10; i++) {
            // 把数据存入缓冲区，一个array
            publisher.submit("data-" + i);
        }

        Thread.sleep(1000);

    }

    private static class MySubscriber implements Flow.Subscriber<String> {

        // 3. 定义一个订阅关系
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            // 背压：从上游请求一个元素
            subscription.request(1);
            System.out.println(Thread.currentThread() + "订阅开始了： " + this.subscription);
        }

        @Override
        public void onNext(String item) {
            // 下一个元素到达时
            System.out.println(Thread.currentThread() + "接收到数据： " + item);
        }

        @Override
        public void onError(Throwable throwable) {
            // 出现异常时
            System.out.println(Thread.currentThread() + "出现异常： " + throwable);
        }

        @Override
        public void onComplete() {
            System.out.println(Thread.currentThread() + "订阅结束了");
        }
    }

}
