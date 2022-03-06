package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class SizeInfoExpressionConverter implements IExpressionConverter<SizeInfo> {
    @Override
    public SizeInfo FromExpressionResult(SizeInfo original, ExpressionValue expressionValue, int param2,
                                         AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public ExpressionValue ToExpressionResult(SizeInfo value, int param1, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public String FormattedValue(IUnit unit, SizeInfo value, int param2, AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public SizeInfo GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObject) {
        return null;
    }
}
