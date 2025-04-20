package org.space.yavin.alex.agent.interfaces;

import org.space.yavin.alex.agent.application.RegistryService;
import org.space.yavin.alex.agent.domain.agent.yishao.YiShaoAgent;
import org.space.yavin.alex.agent.domain.base.entity.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author : Alex Huangfu
 * @Date: 2025/4/6 17:15
 * @Description: ToDo
 */

@RestController
@RequestMapping("/agent/yishao")
public class YiShaoAppController {

    private final YiShaoAgent agent;

    private YiShaoAppController() {
        this.agent = YiShaoAgent.create();
    }

    /**
     * 一勺海龟汤对话接口
     *
     * @return agent回复
     */
    @PostMapping("/chat")
    public Flux<ResponseEntity<Message>> chat(@RequestBody List<Message> chatRequest) {
        return agent.process(chatRequest).map(ResponseEntity::ok);
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
