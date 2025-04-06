package org.space.yavin.alex.agent.domain.agent;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.RegistryService;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * @author yyHuangfu
 * @create 2024/11/17
 */
@SpringBootTest
class TestSimpleAgentDemo {

    @Test
    void chatTest() {
        BaseChatModel model = RegistryService.getLlm("qwen-chat");
        BasicAgent agent = new BasicAgent(
                null, model,
                "你是一个对话机器人",
                "ai chat",
                "chat demo"
        );
        Message msg = new TextMessage("user", "你好");
        // assistant agent
        // todo 完成Memory类
        agent.process(Lists.list(msg));
    }

}