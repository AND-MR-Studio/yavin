package org.space.yavin.alex.agent.infrastructure.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
     * 支持两种方式：
     * 1. 绝对路径：以/开头的路径
     * 2. 类路径资源：不以/开头的路径，会从classpath中加载
     *
     * @param path 文件路径
     * @return 文件内容字符串
     * @throws IOException 文件读取异常
     */
    public static String readFileToString(String path) throws IOException {
        if (path.startsWith("/")) {
            // 绝对路径方式读取
            return Files.readString(Path.of(path), StandardCharsets.UTF_8);
        } else {
            // 从classpath读取资源
            ClassPathResource resource = new ClassPathResource(path);
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                return FileCopyUtils.copyToString(reader);
            }
        }
    }
}