package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class MayRouteExpressionConverter implements IExpressionConverter<MovementState>{
    @Override
    public MovementState FromExpressionResult(MovementState original, ExpressionValue expressionValue, int param2,
                                              AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public ExpressionValue ToExpressionResult(MovementState value, int param1, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public String FormattedValue(IUnit unit, MovementState value, int param2, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public MovementState GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObject) {
        return null;
    }
}
