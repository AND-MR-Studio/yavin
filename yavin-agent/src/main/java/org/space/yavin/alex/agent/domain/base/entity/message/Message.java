package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;

import java.util.List;

import static org.space.yavin.alex.agent.infrastructure.constant.LlmConstants.ASSISTANT;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.DEDUCTION,     // 启用字段推导模式
//        defaultImpl = Message.class      // 推导失败时使用Text类
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(TextMessage.class),
//        @JsonSubTypes.Type(MultimodMessage.class)
//})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private static final Logger logger = LogManager.getLogger(Message.class);

    private String role;
    @Nullable
    private String name;


    public Object getContent() {
        logger.warn("get the Parent Message Class, return empty Str content");
        return "";
    }

    public static Message ofAssistant(Object content) {
        if (content instanceof String) {
            return new TextMessage(ASSISTANT, (String) content);
        } else if (content instanceof List<?> &&
                !((List<?>) content).isEmpty() &&
                ((List<?>) content).get(0) instanceof ContentItem) {

            return new MultimodMessage(ASSISTANT, (List<ContentItem>) content);
        }
        return new TextMessage(ASSISTANT, ""); // todo 兜底回复
    }


}
