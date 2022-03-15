package org.licho.brain.brainApi;

import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ExpressionDataFormat;
import org.licho.brain.brainApi.enu.LogExpressionGanttColorType;
import org.licho.brain.brainApi.enu.LogExpressionItemTextType;

/**
 *
 */
public interface ILogExpression {
    String getDisplayName();

    void setDisplayName(String name);

    String getExpression();

    void setExpression(String expression);

    UnitType getUnitType();

    void setUnitType(UnitType type);

    ExpressionDataFormat getDataFormat();

    void setDataFormat(ExpressionDataFormat dataFormat);

    LogExpressionGanttColorType getGantColorType();

    void setGanttColorType(LogExpressionGanttColorType logExpressionGanttColorType);

    LogExpressionItemTextType getExpressionItemTextType();

    void setExpressionItemTextType(LogExpressionItemTextType expressionItemTextType);
    
}
