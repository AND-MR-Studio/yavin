package org.space.yavin.alex.agent.domain.tool;

import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;

import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/21
 */
@RegisterTool(name = "storage")
public class Storage extends BaseTool<String> {

    public Storage(Map<String, Object> cfg) {
        super(cfg);
    }

    @Override
    public String call(Map<String, Object> params) {
        return "";
    }
}
