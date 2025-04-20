package org.space.yavin.alex.agent.domain.agent.yishao;

import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.application.RegistryService;
import org.space.yavin.alex.agent.domain.agent.Agent;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.space.yavin.alex.agent.infrastructure.utils.FileUtil;
import org.space.yavin.alex.agent.infrastructure.utils.VariablesReplaceUtil;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.space.yavin.alex.agent.domain.llm.KimiChatModel.KIMI_CHAT;
import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.SYSTEM;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:17
 * @Description: 一勺海龟汤小程序
 */
@Slf4j
public class YiShaoAgent extends Agent {

    private static final String YISHAO_NAME = "海龟汤游戏智能体";
    private static final String YISHAO_DESCRIPTION = "一个专业的海龟汤游戏智能体，能够熟练且流畅地与用户进行海龟汤游戏互动。";


    private YiShaoAgent(List<BaseTool<?>> tools, BaseChatModel llm, String promptTemplate, String name, String description) {
        super(tools, llm, promptTemplate, name, description);
    }

    public static YiShaoAgent create() {
        BaseChatModel kimiModel = RegistryService.getLlm(KIMI_CHAT);
        List<BaseTool<?>> tools = Collections.emptyList();
        try {
            // 修改文件路径为类路径资源路径，并确保文件位于src/main/resources/files目录下
            String promptTemplate = FileUtil.readFileToString("yavin-agent/src/main/resources/files/yishao-prompt-template.txt");
            return new YiShaoAgent(tools, kimiModel, promptTemplate, YISHAO_NAME, YISHAO_DESCRIPTION);
        } catch (IOException e) {
            log.error("创建海龟汤智能体失败", e);
        }
        return null;
    }

    @Override
    protected Flux<Message> process(List<Message> messages, Map<String, Object> addInfo) {
        Map<String, String> variables = addInfo.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof String)
                .collect(toMap(Map.Entry::getKey, entry -> (String) entry.getValue(), (o, n) -> n));
        String systemMessage = VariablesReplaceUtil.replaceVariables(super.getSystemMessage(), variables);
        messages.addFirst(new TextMessage(SYSTEM, systemMessage));
        return getLlm().chat(messages, List.of(), false, Collections.emptyMap()).map(List::getFirst);
    }


}
