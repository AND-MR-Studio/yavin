package org.space.yavin.alex.agent.domain.base.entity.message;

import lombok.Data;
import lombok.Setter;
import org.space.yavin.alex.agent.domain.base.entity.content.ContentItem;
import org.space.yavin.alex.agent.domain.base.model.FunctionCall;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/12/22
 */

@Data
public class ListMessage extends Message {

    @Setter
    private List<ContentItem> content;

    public ListMessage(List<ContentItem> content, String role, String name,
                       FunctionCall functionCall) {
        super(role, name, functionCall);
        this.content = content;
    }
}

