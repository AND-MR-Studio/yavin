package org.space.yavin.alex.agent.domain.base;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */
public abstract class BaseTool {
    private String name;
    private String desc;
    private List<Map> params;
}
