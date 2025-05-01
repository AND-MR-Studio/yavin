package org.space.yavin.alex.agent.application.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.space.yavin.alex.agent.interfaces.dto.DialogRequest;

import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/5/1
 */

@Getter
@RequiredArgsConstructor(staticName = "of")
public class DialogContext {

    private final DialogRequest dialogRequest;
    private final Map<String, String> placeholders;
}
