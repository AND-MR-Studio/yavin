package org.space.yavin.alex.agent.domain.tool.yishao;

import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;

import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/4/10
 */

@RegisterTool(name = "yishao_clarify")
public class YiShaoClarify extends BaseTool<String> {
    private final String CLARIFY_QUERY = "clarify_query";

    public YiShaoClarify(Map<String, Object> cfg) {
        super("yishao_clarify", "", null, cfg);
    }

    @Override
    public String call(Map<String, Object> params) {
        return String.valueOf(params.get(CLARIFY_QUERY));
    }
}
