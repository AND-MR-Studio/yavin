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



