package org.space.yavin.alex.agent.infrastructure.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @author yyHuangfu
 * @create 2024/10/17
 */
public class SnakeJsonUtil {

    // 单例模式，确保只有一个 ObjectMapper 实例
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 配置 ObjectMapper 使用蛇形命名法
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // 配置输出格式化的 JSON（可选）
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // 私有构造函数，防止实例化
    private SnakeJsonUtil() {
    }

    /**
     * 将对象序列化为 JSON 字符串。
     *
     * @param obj 要序列化的对象
     * @return JSON 字符串
     * @throws IOException 序列化过程中发生错误
     */
    public static String toJsonStr(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromObj(Object json, TypeReference<T> typeReference) {
        return objectMapper.convertValue(json, typeReference);
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的对象。
     *
     * @param json  JSON 字符串
     * @param clazz 目标对象的类
     * @param <T>   目标对象的类型
     * @return 反序列化后的对象
     * @throws IOException 反序列化过程中发生错误
     */
    public static <T> T fromJsonStr(String json, TypeReference<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     *
     * @param json
     * @param clazz
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> T fromJsonStr(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }


}
