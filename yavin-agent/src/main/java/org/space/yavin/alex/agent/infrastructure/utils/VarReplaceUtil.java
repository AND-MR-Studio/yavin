package org.space.yavin.alex.agent.infrastructure.utils;

import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/4/7
 */
public class VarReplaceUtil {

    String replaceVar(String template, Map<String, String> variables) {
        // 参数校验
        if (template == null || variables == null) {
            throw new IllegalArgumentException("Template and variables must not be null");
        }

        StringBuilder result = new StringBuilder(template);
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String key = "{{" + entry.getKey() + "}}";
            int index;
            while ((index = result.indexOf(key)) != -1) {
                result.replace(index, index + key.length(), entry.getValue());
            }
        }
        return result.toString();
    }
}
