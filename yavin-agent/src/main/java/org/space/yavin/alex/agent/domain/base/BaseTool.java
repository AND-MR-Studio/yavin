package org.space.yavin.alex.agent.domain.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */
public abstract class BaseTool<T> {
    private static String name = "";
    private static String description = "";
    private static List<Map<String, Object>> parameters = List.of();
    private Map<String, Object> cfg;

    public BaseTool(Map<String, Object> cfg) {
        this.cfg = Optional.ofNullable(cfg).orElse(Map.of());
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException(
                    "You must set " + this.getClass().getSimpleName() + ".name, either by @register_tool(name=...) or explicitly setting " + this.getClass().getSimpleName() + ".name"
            );
        }
        if (this.parameters instanceof Map) {
            if (!isToolSchema(Map.of("name", this.name, "description", this.description, "parameters", this.parameters))) {
                throw new IllegalArgumentException(
                        "The parameters, when provided as a dict, must conform to a valid openai-compatible JSON schema."
                );
            }
        }
    }

    public abstract T call(Map<String, Object> params);

    /**
     * Check if obj is a valid JSON schema describing a tool compatible with OpenAI's tool calling.
     * Example valid schema:
     * {
     * "name": "get_current_weather",
     * "description": "Get the current weather in a given location",
     * "parameters": {
     * "type": "object",
     * "properties": {
     * "location": {
     * "type": "string",
     * "description": "The city and state, e.g. San Francisco, CA"
     * },
     * "unit": {
     * "type": "string",
     * "enum": ["celsius", "fahrenheit"]
     * }
     * },
     * "required": ["location"]
     * }
     * }
     *
     * @param schema
     * @return
     */
    private boolean isToolSchema(Map<String, Object> schema) {
        return true;
    }
}
