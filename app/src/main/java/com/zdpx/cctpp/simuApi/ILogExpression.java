package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.simioEnums.ExpressionDataFormat;
import com.zdpx.cctpp.simuApi.enu.LogExpressionGanttColorType;
import com.zdpx.cctpp.simuApi.enu.LogExpressionItemTextType;

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
