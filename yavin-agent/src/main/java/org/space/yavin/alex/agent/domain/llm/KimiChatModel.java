package org.space.yavin.alex.agent.domain.llm;

import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/3/29
 */
public class KimiChatModel extends BaseChatModel {
    @Override
    protected Flux<List<Message>> chatStream(List<Message> messages, Map<String, Object> cfg) {
        return null;
    }

    @Override
    protected List<Message> chatNoStream() {
        return List.of();
    }

    @Override
    protected Flux<List<Message>> chatWithFunction() {
        return null;
    }
}
