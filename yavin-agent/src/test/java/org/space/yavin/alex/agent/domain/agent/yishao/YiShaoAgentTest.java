package org.space.yavin.alex.agent.domain.agent.yishao;

import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.KimiChatModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yyHuangfu
 * @create 2025/4/10
 */
class YiShaoAgentTest {

    @Test
    void process() {
        YiShaoAgent agent = YiShaoAgent.create();
        agent.process(List.of(new Message("user", "你好"))).blockLast();
    }
}