package org.space.yavin.alex.agent.infrastructure;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2024/10/20
 */
public class Settings {

    // Settings for LLMs
    public static final int DEFAULT_MAX_INPUT_TOKENS = 5800;  // LLM will truncate the input messages if they exceed this limit

    // Settings for agents
    public static final int MAX_LLM_CALL_PER_RUN = 8;

    // Settings for tools
    public static final String DEFAULT_WORKSPACE = "workspace";

    // Settings for RAG
    public static final int DEFAULT_MAX_REF_TOKEN = 4000;  // The window size reserved for RAG materials
    public static final int DEFAULT_PARSER_PAGE_SIZE = 500;  // Max tokens per chunk when doing RAG
    public static final String DEFAULT_RAG_KEYGEN_STRATEGY = "SplitQueryThenGenKeyword";  // Possible values: 'None', 'GenKeyword', 'SplitQueryThenGenKeyword', 'GenKeywordWithKnowledge', 'SplitQueryThenGenKeywordWithKnowledge'
    public static final List<String> DEFAULT_RAG_SEARCHERS = List.of("keyword_search", "front_page_search");  // Sub-searchers for hybrid retrieval
}

