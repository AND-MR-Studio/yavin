package org.space.yavin.alex.agent.application;

import org.space.yavin.alex.agent.domain.agent.yishao.YiShaoAgent;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.interfaces.dto.DialogRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/12 19:24
 * @Description: ToDo
 */

@Service
public class YiShaoAgentApplication {


    public Flux<Message<?>> chat(DialogRequest request) {

        return YiShaoAgent.create(request.getPlaceholders()).process(request.getMessages());
    }

}
