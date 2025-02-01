# hello webflux

## reactive-stream

1. 处理可能无限数量的元素
2. 有序
3. 在组件之间异步传递元素
4. 强制性非阻塞, 背压模式(消费快，正压是生产多，如tomcat处理请求)

引入缓冲区+消息队列，就能实现全异步，不阻塞不等待，实时响应。

### 数据管道
Publisher发布者 -> [ Processor处理器 -> Processor处理器 -> Processor处理器 ] -> Subscriber订阅者
处理器既是publisher又是subscriber

https://projectreactor.io/docs/core/release/reference/reactiveProgramming.html <br>
Using an iterator is an imperative programming pattern, even though the method of accessing values is solely the responsibility of the Iterable. Indeed, it is up to the developer to choose when to access the next() item in the sequence. In reactive streams, the equivalent of the above pair is Publisher-Subscriber. But it is the Publisher that notifies the Subscriber of newly available values as they come, and this push aspect is the key to being reactive. Also, operations applied to pushed values are expressed declaratively rather than imperatively: The programmer expresses the logic of the computation rather than describing its exact control flow.

Reactive可以解决Callback Hell问题

