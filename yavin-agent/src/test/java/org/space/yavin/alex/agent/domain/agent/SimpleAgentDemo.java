package org.space.yavin.alex.agent.domain.agent;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.RegistryService;
import org.space.yavin.alex.agent.domain.base.model.Message;
import org.space.yavin.alex.agent.domain.llm.QwenChatAtDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yyHuangfu
 * @create 2024/11/17
 */
@SpringBootTest
class SimpleAgentDemo {

    @Test
    public void chatTest() {
        BaseChatModel model = RegistryService.getLlm("qwen-chat");
        Assistant agent = new Assistant(null, model,
                "你是一个对话机器人", "ai chat", "chat demo");
        Message msg = new Message("user", "你好");
        agent.run(Lists.list(msg));
    }

}