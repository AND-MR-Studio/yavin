package org.space.yavin.alex.agent.domain.agent.yishao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.application.RegistryService;
import org.space.yavin.alex.agent.domain.agent.Agent;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.infrastructure.utils.FileUtil;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.llm.KimiChatModel.KIMI_CHAT;
import static org.space.yavin.alex.agent.infrastructure.utils.VariablesReplaceUtil.replaceVariables;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:17
 * @Description: 一勺海龟汤小程序
 */
@Slf4j
@Getter
public class YiShaoAgent extends Agent {

    private final Map<String, String> placeHolders;


    private YiShaoAgent(List<BaseTool<?>> tools, BaseChatModel llm, String promptTemplate, String name,
                        String description, Map<String, String> placeHolders) {
        super(tools, llm, promptTemplate, name, description);
        this.placeHolders = placeHolders;
    }

    public static YiShaoAgent create(Map<String, String> placeHolders) {
        BaseChatModel kimiModel = RegistryService.getLlm(KIMI_CHAT);
        List<BaseTool<?>> tools = Collections.emptyList();
        try {
            // 使用类路径方式加载资源，确保在容器环境中也能正确加载
            String promptTemplate = FileUtil.readFileToString("files/yishao-prompt-template.txt");
            return new YiShaoAgent(tools, kimiModel, promptTemplate, "海龟汤游戏智能体",
                    "一个专业的海龟汤游戏智能体，能够熟练且流畅地与用户进行海龟汤游戏互动。", placeHolders);
        } catch (IOException e) {
            log.error("创建海龟汤智能体失败", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    protected Flux<Message<?>> process(List<Message<?>> messages, Map<String, Object> cfg) {
        return getLlm().chat(messages, List.of(), false, Collections.emptyMap()).map(List::getFirst);
    }

    @Override
    public String getSystemMessage() {
        return replaceVariables(super.getSystemMessage(), getPlaceHolders());
    }
}
