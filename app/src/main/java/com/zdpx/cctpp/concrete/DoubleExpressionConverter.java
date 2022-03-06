package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class DoubleExpressionConverter implements IExpressionConverter<Double> {
    @Override
    public Double FromExpressionResult(Double original, ExpressionValue expressionValue, int param2,
                                       AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public ExpressionValue ToExpressionResult(Double value, int param1, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public String FormattedValue(IUnit unit, Double value, int param2, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public Double GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObject) {
        return null;
    }
}
