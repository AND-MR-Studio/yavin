package org.space.yavin.alex.agent.domain.base

/**
 * @author yyHuangfu
 * @create 2024/11/18
 */
class BaseSearch(cfg: Map<String, Any>, private var maxRefToken: Int) : BaseTool<List<Any>>(cfg) {
    var description = "从给定文档中检索和问题相关的部分"
    var parameters = listOf(
        mapOf(
            "name" to "query",
            "type" to "string",
            "description" to "问题，需要从文档中检索和这个问题有关的内容",
            "required" to true
        )
    )


    /**
     * The basic search algorithm
     *  Args:
     *      params: The dict parameters.
     *      docs: The list of parsed doc, each doc has unique url.
     *
     *  Returns:
     *      The list of retrieved chunks from each doc.
     */
    override fun call(params: MutableMap<String, Any>?): List<Any> {
        TODO("Not yet implemented")
    }

}