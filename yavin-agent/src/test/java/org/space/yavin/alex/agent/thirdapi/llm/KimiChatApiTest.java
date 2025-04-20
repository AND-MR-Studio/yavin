package org.space.yavin.alex.agent.thirdapi.llm;

import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.thirdapi.llm.response.kimi.KimiApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * KimiChatApi单元测试类
 *
 * @author yyHuangfu
 * @create 2024/3/29
 */
@SpringBootTest
@Import(KimiChatApiTestConfig.class)
@TestPropertySource(properties = {
        "rpc.api-config.kimi-chat.url=https://api.moonshot.cn/v1/chat/completions",
        "rpc.api-config.kimi-chat.timeout=5000",
        "rpc.api-config.api-key.kimi=sk-0TsvAhmvcwF8W0oLc5TTq5kPrHx91zQum9S2nONTiLkSqlg3"
})
class KimiChatApiTest {

    @Autowired
    private KimiChatApi kimiChatApi;

    private final String MODEL = "moonshot-v1-8k";

    /**
     * 测试正常调用场景
     */
    @Test
    void testCallSuccess() {
        // 准备测试数据
        List<Message> messages = List.of(
                new TextMessage("system", "你是一个专业的海龟汤游戏智能体，能够熟练且流畅地与用户进行海龟汤游戏互动。"),
                new TextMessage("user", "你好，我叫李雷，1+1等于多少？")
        );

        // 执行测试
        KimiApiResponse result = kimiChatApi.call(
                MODEL, null, null, messages, null).blockFirst();
        assert result != null;
        System.out.println(result.getChoices().getFirst().getMessage().getContent());
    }
}