package org.space.yavin.alex.agent.domain.entity.yishao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.space.yavin.alex.agent.infrastructure.utils.VariablesReplaceUtil;

import java.util.List;

/**
 * @author yyHuangfu
 * @create 2025/5/2
 */
@Getter
@AllArgsConstructor
public class PlaceHolder implements VariablesReplaceUtil.VarPlaceholder {
    private final String puzzleSurface;
    private final String puzzleTruth;
    private final List<String> keyClues;
}
