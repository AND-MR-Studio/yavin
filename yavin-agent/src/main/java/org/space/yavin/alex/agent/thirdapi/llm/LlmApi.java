package org.space.yavin.alex.agent.thirdapi.llm;

import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.thirdapi.llm.response.GenerationResponse;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author yyHuangfu
 * @create 2024/10/18
 */
public interface LlmApi {

    /**
     * @param model     请求的模型，例如 `qwen-turbo`。
     * @param prompt    输入提示。
     * @param history   用户提供的历史记录，已弃用。
     *                  示例：
     *                  <pre>{"user": "The weather is fine today.", "bot": "Suitable for outings"}</pre>
     *                  默认值为 `null`。
     * @param messages  生成的消息。
     *                  示例：
     *                  <pre>{"role": "user", "content": "The weather is fine today."},
     *                                                                                                       {"role": "assistant", "content": "Suitable for outings"}</pre>
     * @param plugins   插件配置。可以是插件配置字符串或字典。
     * @param workspace DashScope 工作区 ID。
     * @param addInfo   额外的参数，包括但不限于：
     *                  <ul>
     *                      <li><code>stream</code> (boolean, 可选)：启用服务器发送事件（SSE）。
     *                          结果将部分返回（适用于 `qwen-turbo`, `bailian-v1`）。
     *                      <li><code>temperature</code> (float, 可选)：控制生成文本的随机性和多样性。
     *                          范围：0 到 2。较高的值会使结果更随机，较低的值会使结果更确定（适用于 `qwen-turbo`, `qwen-plus`）。
     *                      <li><code>top_p</code> (float, 可选)：核采样策略，模型考虑概率质量最高的前 p% 的 token。
     *                          例如，0.1 表示仅考虑概率最高的 10% 的 token（适用于 `qwen-turbo`, `bailian-v1`）。
     *                      <li><code>top_k</code> (int, 可选)：生成时的候选集大小。
     *                          例如，50 表示每次生成时选择概率最高的 50 个 token 形成候选集。
     *                          较大的值增加随机性，较小的值增加确定性。默认值为 0，表示不启用 top_k 策略（此时仅 top_p 生效）。
     *                      <li><code>enable_search</code> (boolean, 可选)：是否启用网页搜索（Quark）。
     *                          目前仅在第一轮对话中效果最佳，默认值为 `false`（支持模型：`qwen-turbo`）。
     *                      <li><code>customized_model_id</code> (str, 必填)：企业专属大模型 ID，
     *                          需从企业专属大模型产品的操作后台生成（支持模型：`bailian-v1`）。
     *                      <li><code>result_format</code> (str, 可选)：设置结果格式，可选值为 `message` 或 `text`，默认值为 `text`。
     *                      <li><code>incremental_output</code> (boolean, 可选)：控制流式输出模式。
     *                          如果为 `true`，后续输出将包含之前输入的内容；否则，后续输出将不包含之前输出的内容，默认值为 `false`。
     *                      <li><code>stop</code> (list[str] 或 list[list[int]], 可选)：控制生成停止的条件。
     *                          当遇到设置的字符串或 token ID 时，生成将停止，结果将不包含停止词或 token。
     *                      <li><code>max_tokens</code> (int, 可选)：期望的最大输出 token 数量。
     *                          注意：生成的长度可能小于 `max_tokens`，不一定等于它。如果 `max_tokens` 设置过大，服务将提示长度超出限制，一般不建议设置此值。
     *                      <li><code>repetition_penalty</code> (float, 可选)：控制生成模型的重复性。
     *                          增加 `repetition_penalty` 可以减少生成的重复内容，1.0 表示无惩罚。
     *                  </ul>
     */
    Flux<GenerationResponse> call(
            String model,
            Object prompt,
            List<Message> history,
            List<Message> messages,
            Object plugins,
            String workspace,
            Map<String, Object> addInfo
    );

}
