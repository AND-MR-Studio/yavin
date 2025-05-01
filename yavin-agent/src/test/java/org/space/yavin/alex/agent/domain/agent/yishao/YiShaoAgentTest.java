package org.space.yavin.alex.agent.domain.agent.yishao;

import org.junit.jupiter.api.Test;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.domain.llm.KimiChatModel;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yyHuangfu
 * @create 2025/4/10
 */
class YiShaoAgentTest {

    @Test
    void process() {
        YiShaoAgent agent = YiShaoAgent.create(Map.of());
        agent.process(List.of(Message.ofUser("你好"))).blockLast();
    }
}