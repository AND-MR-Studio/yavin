package org.space.yavin.alex.agent.interfaces

import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux

/**
 * @author yyHuangfu
 * @create 2024/10/6
 */
class QueryStreamController {

    @PostMapping("/stream-query")
    fun internalStreamPost(
        serverHttpRequest: ServerHttpRequest,
        @RequestBody dialogRequest: DialogRequest
    ): ResponseEntity<Flux<StreamDialogResult>> {
        val res = StreamDialogResult("init");
        return ResponseEntity.ok(Flux.just(res))
    }
}

class DialogRequest{
    var query: String? = null
}

class StreamDialogResult constructor(var content: String){
}