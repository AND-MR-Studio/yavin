package org.space.yavin.alex.agent.domain.flux;


import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Flow;

/**
 * @author yyHuangfu
 * @create 2025/1/22
 */
public class SubAndPubDemo {

    public static void main(String[] args) throws InterruptedException {
        MyPublisher<String> publisher = new MyPublisher<>();

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

    /**
     * 订阅关系, 想具体理解，可以看{@link java.util.concurrent.SubmissionPublisher}中的BufferedSubscription
     * submit后，数据会存入subscription的buffer中，
     */
    @Getter
    private static class MySubscription implements Flow.Subscription {
        private final MyPublisher<String> publisher;
        private final MySubscriber subscriber;
        private final Queue<String> buffer;

        private long requested = 0;

        public MySubscription(MyPublisher<String> publisher, MySubscriber subscriber) {
            this.publisher = publisher;
            this.subscriber = subscriber;
            this.buffer = new LinkedList<>();
        }

        @Override
        public void request(long n) {
            if (n <= 0) {
                subscriber.onError(new IllegalArgumentException("Non-positive request value"));
                return;
            }
            requested += n;
            sendItems();
        }

        @Override
        public void cancel() {
            publisher.subscriber = null;
        }

        private void sendItems() {
            while (requested > 0 && !buffer.isEmpty()) {
                subscriber.onNext(buffer.poll());
                requested--;
            }
        }
    }

    private static class MyPublisher<T> implements Flow.Publisher<T> {
        private MySubscriber subscriber;
        private MySubscription client;

        @Override
        public void subscribe(Flow.Subscriber<? super T> subscriber) {
            MySubscriber sub = (MySubscriber) subscriber;
            this.subscriber = sub;
            // publisher负责创建subscription
            client = new MySubscription((MyPublisher<String>) this, sub);
            sub.onSubscribe(client);
        }

        public void submit(String item) {
            client.buffer.add(item);
            client.sendItems();
        }
    }

    @Getter
    private static class MySubscriber implements Flow.Subscriber<String> {

        // 3. 定义一个订阅关系
        private MySubscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = (MySubscription) subscription;
            // 背压：从上游请求一个元素
            subscription.request(1);
            System.out.println(Thread.currentThread() + "订阅开始了： " + this.subscription);
        }

        @Override
        public void onNext(String item) {
            // 下一个元素到达时
            System.out.println(Thread.currentThread() + "接收到数据： " + item);
            subscription.request(1);
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
