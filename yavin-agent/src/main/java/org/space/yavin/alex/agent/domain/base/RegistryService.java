package org.space.yavin.alex.agent.domain.base;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterLlm;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;
import org.space.yavin.alex.agent.domain.llm.base.BaseChatModel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

import static cn.hutool.extra.spring.SpringUtil.getBeanFactory;

/**
 * @author yyHuangfu
 * @create 2024/10/21
 */

@Service
public class RegistryService {

    private static final Logger logger = LogManager.getLogger(RegistryService.class);
    private static final Map<String, Class<BaseTool<?>>> TOOL_REGISTRY = new HashMap<>();
    private static final Map<String, BaseChatModel> LLM_REGISTRY = new HashMap<>();

    public static void registerTool(String name, Class<BaseTool<?>> toolClz) {
        TOOL_REGISTRY.put(name, toolClz);
    }

    public static Class<BaseTool<?>> getTool(String name) {
        return TOOL_REGISTRY.get(name);
    }

    public static void registerLlm(String name, BaseChatModel llm) {
        LLM_REGISTRY.put(name, llm);
    }

    public static BaseChatModel getLlm(String name) {
        return LLM_REGISTRY.get(name);
    }

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {

        List<Class<?>> toolList = findClassesWithAnnotation("org.space.yavin.alex.agent.domain.tool", RegisterTool.class);

        for (Class<?> tool : toolList) {
            registerTool(tool.getAnnotation(RegisterTool.class).name(), (Class<BaseTool<?>>) tool);
        }

        Collection<Object> llmList = getBeanFactory().getBeansWithAnnotation(RegisterLlm.class).values();
        for (Object llm : llmList) {
            registerLlm(llm.getClass().getAnnotation(RegisterLlm.class).name(), (BaseChatModel) llm);
        }
    }

    public static List<Class<?>> findClassesWithAnnotation(String packageName, Class<? extends Annotation> annotationClass)
            throws ClassNotFoundException, IOException {
        List<Class<?>> classes = getClasses(packageName);
        List<Class<?>> annotatedClasses = new ArrayList<>();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(annotationClass)) {
                annotatedClasses.add(clazz);
            }
        }
        return annotatedClasses;
    }

    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
