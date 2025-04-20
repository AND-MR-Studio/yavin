package org.space.yavin.alex.agent.infrastructure.resolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.space.yavin.alex.agent.domain.base.entity.message.TextMessage;

public class MessageTypeResolver extends TypeIdResolverBase {
    @Override
    public String idFromValue(Object value) {
        return null;
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return null;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        // 此处的 id 实际上是 "content"
        if (id instanceof String) {
            return TypeFactory.defaultInstance().constructType(TextMessage.class);
        }
        throw new IllegalStateException("Unknown message type");
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}