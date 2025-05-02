package org.space.yavin.alex.agent.interfaces;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.space.yavin.alex.agent.application.YiShaoAgentApplication;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.space.yavin.alex.agent.infrastructure.utils.SnakeJsonUtil;
import org.space.yavin.alex.agent.interfaces.dto.DialogRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:15
 * @Description: ToDo
 */

@Slf4j
@RestController
@RequestMapping("/agent/yishao")
@AllArgsConstructor
public class YiShaoAppController {

    private final YiShaoAgentApplication yishaoApp;

    /**
     * 一勺海龟汤对话接口
     *
     * @return agent回复
     */
    @PostMapping("/chat")
    public Flux<Message<?>> chat(@RequestBody DialogRequest dialogRequest) throws IOException {
        log.info("对话请求：{}", SnakeJsonUtil.toJsonStr(dialogRequest));
        return yishaoApp.chat(dialogRequest);
    }

    /**
     * 健康检查接口
     *
     * @return 健康状态
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        // 这里可以添加实际的健康检查逻辑
        return ResponseEntity.ok("HEALTHY");
    }

    // 可选扩展：参数化查询示例
    @GetMapping("/echo")
    public ResponseEntity<String> echo(@RequestParam String message) {
        return ResponseEntity.ok("Received: " + message);
    }
}
