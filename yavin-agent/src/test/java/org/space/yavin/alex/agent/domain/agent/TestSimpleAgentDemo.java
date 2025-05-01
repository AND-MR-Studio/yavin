package org.space.yavin.alex.agent.domain.agent;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.application.RegistryService;
import org.space.yavin.alex.agent.domain.base.entity.content.Content;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.springframework.boot.test.context.SpringBootTest;

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

        Message<Content> msg = Message.ofUser("你好");
        // assistant agent
        // todo 完成Memory类
        agent.process(Lists.list(msg));
    }

}