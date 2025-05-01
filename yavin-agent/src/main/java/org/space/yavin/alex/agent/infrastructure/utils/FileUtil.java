package org.space.yavin.alex.agent.infrastructure.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:22
 * @Description: 文件操作工具类
 */
public class FileUtil {

    /**
     * 读取文件内容为字符串（使用UTF-8编码）
     *
     * @param path 文件路径
     * @return 文件内容字符串
     * @throws IOException 文件读取异常
     */
    public static String readFileToString(String path) throws IOException {
        return Files.readString(Path.of(path), StandardCharsets.UTF_8);
    }
}