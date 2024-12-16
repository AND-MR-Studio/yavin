package org.space.yavin.alex.agent.domain.agent;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.BaseChatModel;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.model.ContentItem;
import org.space.yavin.alex.agent.domain.base.model.Message;
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
    public Flux<Message<?>> run(List<Message<?>> messages) {
        // todo 为什么这里需要深拷贝
        List<Message<?>> newMsgs = new ArrayList<>(messages);
        // todo lang判断, 增加addInfo参数
        Map<String, ?> addInfo = new HashMap<>();

        return _run(newMsgs, addInfo).flatMap(rsp -> {
            // 确保响应的名称不为空
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

    protected abstract Flux<Message<?>> _run(List<Message<?>> messages, Map<String, Object> addInfo);

    protected Flux<Message<?>> callLlm(List<Message<?>> messages) {
        if (StrUtil.isNotBlank(this.systemMessage)) {
            Message<?> firstMsg = messages.get(0);
            if (!firstMsg.getRole().equals(SYSTEM)) {
                messages.add(0, new Message<>(SYSTEM, this.systemMessage));
            } else if (firstMsg.getContent() instanceof String) {
                firstMsg.setContent(this.systemMessage + "\n\n" + messages.get(0).getContent());
            } else {
                assert firstMsg.getContent() instanceof List;
                @SuppressWarnings("unchecked")
                List<ContentItem> contentList = (List<ContentItem>) messages.get(0).getContent();

                contentList.add(0, new ContentItem(this.systemMessage + "\n\n"));
            }
        }
    }

}