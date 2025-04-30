package org.space.yavin.alex.agent.domain.base.entity.message;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/12/22
 * @description 多模态Message
 */

@Setter
@Getter
public class MultimodMessage extends Message {

    private List<ContentItem> content;


    public MultimodMessage(String role, List<ContentItem> content) {
        super(role, null);
        this.content = content;
    }
}

