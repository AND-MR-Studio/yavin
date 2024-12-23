package org.space.yavin.alex.agent.domain.base.entity.message;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.space.yavin.alex.agent.domain.base.model.FunctionCall;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String role;
    @Nullable
    private String name;
    @Nullable
    private FunctionCall functionCall;

}
