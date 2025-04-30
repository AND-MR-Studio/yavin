package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.MultimodMessage;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.SYSTEM;

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
     * @param messages 消息列表。
     * @return 响应生成器。
     */
    public Flux<Message> process(List<Message> messages) {
        // todo 为什么这里需要深拷贝
        List<Message> newMsgs = new ArrayList<>(messages);
        // todo lang判断, 增加addInfo参数
        Map<String, Object> addInfo = new HashMap<>();

        if (CharSequenceUtil.isNotBlank(getSystemMessage())) {
            Message firstMsg = messages.getFirst();
            // 如果第一个消息不是系统消息，则添加系统消息
            if (!firstMsg.getRole().equals(SYSTEM)) {
                messages.addFirst(new TextMessage(SYSTEM, getSystemMessage()));
                // 如果第一个消息是字符串，则将系统消息添加到第一个消息的开头
            } else if (firstMsg instanceof TextMessage) {
                ((TextMessage) firstMsg).setContent(getSystemMessage() + "\n\n" + ((TextMessage) firstMsg).getContent());
                // 如果第一个消息是列表
            }
//            else if (firstMsg instanceof MultimodMessage) {
//                List<ContentItem> contentList = ((MultimodMessage) firstMsg).getContent();
//                contentList.addFirst(new ContentItem(ContentType.TEXT, getSystemMessage() + "\n\n"));
//            }
        }
        return process(newMsgs, addInfo).flatMap(rsp -> {
            // 确保响应的名称不为空
            if (StrUtil.isBlank(rsp.getName()) && StrUtil.isNotBlank(getName())) {
                rsp.setName(getName());
            }
            return Flux.just(rsp);
        }).onErrorResume(e -> {
            // 处理异常，例如记录日志
            log.error("Error in run method: " + e.getMessage());
            return Flux.empty();
        });
    }

    protected abstract Flux<Message> process(List<Message> messages, Map<String, Object> addInfo);

    /**
     * agent和LLM的交互接口
     * todo 后续把tools也加上
     *
     * @param messages 消息列表。
     * @param cfg      配置参数。
     * @return LLM生成的内容回复
     */
    protected Flux<List<Message>> callLlm(List<Message> messages, Map<String, Object> cfg) {
        return this.llm.chat(messages, null, true, cfg);
    }

}