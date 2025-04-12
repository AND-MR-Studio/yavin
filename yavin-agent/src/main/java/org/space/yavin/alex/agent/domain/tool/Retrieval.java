package org.space.yavin.alex.agent.domain.tool;

import cn.hutool.core.util.ReflectUtil;
import org.space.yavin.alex.agent.domain.base.BaseSearch;
import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.application.RegistryService;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.space.yavin.alex.agent.domain.tool.DocParser.PARSER_SUPPORTED_FILE_TYPES;
import static org.space.yavin.alex.agent.infrastructure.Settings.*;

/**
 * @author yyHuangfu
 * @create 2024/10/20
 */

@RegisterTool(name = "retrieval")
public class Retrieval extends BaseTool<List<?>> {

    private Integer maxRefToken;
    private Integer parserPageSize;
    private List<String> ragSearchers;
    private String ragKeygenStrategy;
    private DocParser docParser;
    private BaseSearch search;

    private static String description = "从给定文件列表中检索出和问题相关的内容，支持文件类型包括："
            + String.join("/", PARSER_SUPPORTED_FILE_TYPES);
    private static List<Map<String, Object>> parameters = List.of(
            Map.of("name", "query", "type", "string",
                    "description", "在这里列出关键词，用逗号分隔，目的是方便在文档中匹配到相关的内容，" +
                            "由于文档可能多语言，关键词最好中英文都有。", "required", true),
            Map.of("name", "files", "type", "array",
                    "items", Map.of("type", "string"),
                    "description", "待解析的文件路径列表，支持本地文件路径或可下载的http(s)链接", "required", true)
    );


    public Retrieval(Map<String, Object> cfg) {
        super(null, null, null, cfg);
        this.maxRefToken = (Integer) cfg.getOrDefault("max_ref_token", DEFAULT_MAX_REF_TOKEN);
        this.parserPageSize = (Integer) cfg.getOrDefault("parser_page_size", DEFAULT_PARSER_PAGE_SIZE);
        this.ragSearchers = (List<String>) cfg.getOrDefault("rag_searchers", DEFAULT_RAG_SEARCHERS);
        this.ragKeygenStrategy = (String) cfg.getOrDefault("rag_keygen_strategy", DEFAULT_RAG_KEYGEN_STRATEGY);

        this.docParser = new DocParser(cfg); // todo 这样其实不好，隐藏了用法。应该用啥传啥。
        this.search = (BaseSearch) ReflectUtil.newInstance(RegistryService.getTool(this.ragSearchers.get(0)), cfg);
    }

    @Override
    public List<?> call(Map<String, Object> params) {
        List<String> files = (List<String>) params.get("files");
        List<Map<String, Object>> records = new ArrayList<>();
        for (String file : files) {
            Map<String, Object> result = docParser.call(Map.of("url", file));
            records.add(result);
        }
        if (!records.isEmpty()) {
            return this.search.call(Map.of("records", records, "query", params.get("query")));
        }
        return records;
    }
}
