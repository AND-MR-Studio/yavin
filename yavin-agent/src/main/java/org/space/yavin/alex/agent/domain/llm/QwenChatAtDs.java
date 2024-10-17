package org.space.yavin.alex.agent.domain.llm;

import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.BaseFnCallModel;
import org.space.yavin.alex.agent.domain.base.model.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Slf4j
public class QwenChatAtDs extends BaseFnCallModel {
    @Override
    protected Flux<List<Message>> _chatStream(List<Message> messages, Map<String, String> cfg) {
        return null;
    }

    @Override
    protected List<Message> _chatNoStream() {
        return List.of();
    }

    @Override
    protected Flux<List<Message>> _chatWithFunction() {
        return null;
    }
}
