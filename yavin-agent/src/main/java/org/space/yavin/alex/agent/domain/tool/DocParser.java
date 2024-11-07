package org.space.yavin.alex.agent.domain.tool;

import org.space.yavin.alex.agent.domain.base.BaseTool;
import org.space.yavin.alex.agent.domain.base.annotation.RegisterTool;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/21
 */

@RegisterTool(name = "doc_parser")
public class DocParser extends BaseTool<Map> {

    public final static List<String> PARSER_SUPPORTED_FILE_TYPES = List.of("pdf", "doc", "docx", "ppt", "pptx", "txt", "md");

    private static String description = "对一个文件进行内容提取和分块、返回分块后的文件内容";
    private static List<Map<String, Object>> parameters = List.of(
            Map.of("name", "url", "type", "string",
                    "description", "待解析的文件的路径，可以是一个本地路径或可下载的http(s)链接", "required", true)
    );

    private Integer maxRefToken;
    private Integer parserPageSize;
    private String dataRoot;
    private Storage db;
    private SimpleDocParser docExtractor;

    public DocParser(Map<String, Object> cfg) {
        super(cfg);
        this.maxRefToken = (Integer) cfg.get("max_ref_token");
        this.parserPageSize = (Integer) cfg.get("parser_page_size");
    }

    /**
     * """Extracting and blocking
     * <p>
     * Returns:
     * Parse doc as the following chunks:
     * {
     * 'url': 'This is the url of this file',
     * 'title': 'This is the extracted title of this file',
     * 'raw': [
     * {
     * 'content': 'This is one chunk',
     * 'token': 'The token number',
     * 'metadata': {}  # some information of this chunk
     * },
     * ...,
     * ]
     * }
     * """
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> call(Map<String, Object> params) {

        return Map.of();
    }
}

class Chunk {
    String content;
    Integer token;
    Map<String, Object> metadata;
}

class Record {
    String url;
    String title;
    List<Chunk> raw;
}
