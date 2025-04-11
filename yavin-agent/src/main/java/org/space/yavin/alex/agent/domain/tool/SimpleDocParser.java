package org.space.yavin.alex.agent.domain.tool;

import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/21
 */

@RegisterTool(name = "simple_doc_parser")
public class SimpleDocParser extends BaseTool<List<String>> {

    public SimpleDocParser(Map<String, Object> cfg) {
        super(null, null, null, cfg);
    }

    @Override
    public List<String> call(Map<String, Object> params) {
        return List.of();
    }
}
