package org.space.yavin.alex.agent.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2025/4/7
 */
public class VariablesReplaceUtil {

    /**
     * 替换模板中的{{key}}变量占位符
     *
     * @param template    模板
     * @param variables   变量
     * @return 替换后的模板
     */
    public static String replaceVariables(String template, Map<String, String> variables) {
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

    /**
     * 替换模板中的{{key}}变量占位符
     *
     * @param template    模板
     * @param placeholder 变量
     * @return 替换后的模板
     */
    public static String replaceVariables(String template, VarPlaceholder placeholder) {
        Map<String, String> placeholderMap = SnakeJsonUtil.fromObj(placeholder, new TypeReference<>() {});
        return replaceVariables(template, placeholderMap);
    }


    public interface VarPlaceholder {

    }
}
