package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.content.TextContent;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Slf4j
@Getter
@AllArgsConstructor
public abstract class Agent {

    protected List<BaseTool<?>> tools;
    private BaseChatModel llm;
    private String systemMessage;
    private String name;
    private String description;

    /**
     * 返回一个基于接收到的消息的流式响应。
     * 该方法对输入的消息进行统一类型转换，并调用 _run 方法生成回复。
     *
     * @param messages 消息。
     * @return 响应生成器。
     */
    public Flux<Message<?>> process(List<Message<?>> messages) {
        if (CharSequenceUtil.isNotBlank(getSystemMessage())) {
            Message<?> firstMsg = messages.getFirst();
            // 如果第一个消息不是系统消息，则添加系统消息
            if (!RoleEnum.SYSTEM.equals(firstMsg.getRole())) {
                messages.addFirst(Message.ofSystem(getSystemMessage()));
                // 如果第一个消息是字符串，则将系统消息添加到第一个消息的开头
            } else if (firstMsg.getContent() instanceof TextContent textContent) {
                textContent.setText(getSystemMessage() + "\n\n" + textContent.getText());
            } else if (firstMsg instanceof TextMessage textMessage) {
                textMessage.setContent(getSystemMessage() + "\n\n" + textMessage.getContent());
            }
        }
        return process(messages, Map.of()).flatMap(message -> {
                    // 确保响应的名称不为空
                    if (StrUtil.isBlank(message.getName()) && StrUtil.isNotBlank(getName())) {
                        message.setName(getName());
                    }
                    return Flux.just(message);
                }
        );
    }

    protected abstract Flux<Message<?>> process(List<Message<?>> messages, Map<String, Object> cfg);

    /**
     * agent和LLM的交互接口
     * todo 后续把tools也加上
     *
     * @param messages 消息列表。
     * @param cfg      配置参数。
     * @return LLM生成的内容回复
     */
    protected Flux<List<Message<?>>> callLlm(List<Message<?>> messages, Map<String, Object> cfg) {
        return this.llm.chat(messages, null, true, cfg);
    }

}