package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.Message;
import org.space.yavin.alex.agent.domain.llm.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Getter
@AllArgsConstructor
public abstract class Agent {

    private List<BaseTool> tools = new ArrayList<>();
    private BaseChatModel llm;
    private String systemMessage;
    private String name;
    private String description;

    /**
     * 返回一个基于接收到的消息的流式响应。
     * 该方法对输入的消息进行统一类型转换，并调用 _run 方法生成回复。
     *
     * @param messages 消息列表。
     * @return 响应生成器。
     */
    public Flux<Message> run(List<Message> messages) {
        List<Message> newMsgs = new ArrayList<>(messages);
        // todo lang判断

        return _run(newMsgs).flatMap(rsp -> {
            if (StrUtil.isBlank(rsp.getName()) && StrUtil.isNotBlank(getName())) {
                rsp.setName(getName());
            }
            return Flux.just(rsp);
        }).onErrorResume(e -> {
            // 处理异常，例如记录日志
            System.err.println("Error in run method: " + e.getMessage());
            return Flux.empty();
        });
    }

    abstract Flux<Message> _run(List<Message> messages);

}