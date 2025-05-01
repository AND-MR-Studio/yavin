package org.space.yavin.alex.agent.domain.base.entity.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import org.space.yavin.alex.agent.domain.base.enums.RoleEnum;

/**
 * @author yyHuangfu
 * @create 2025/5/1
 */
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // 禁用多态类型识别
public class TextMessage extends Message<String> {

    // todo 待研究：这里为什么非得要加这个注解？，用role,content当变量都不行，不是已经有creator注解吗？
    @JsonCreator
    public TextMessage(
            @JsonProperty("role") RoleEnum role,
            @JsonProperty("content") String text) {
        super(role, text);
    }
}
