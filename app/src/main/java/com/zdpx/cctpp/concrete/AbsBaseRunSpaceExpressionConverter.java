package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class AbsBaseRunSpaceExpressionConverter implements IExpressionConverter<AbsBaseRunSpace> {
    @Override
    public AbsBaseRunSpace FromExpressionResult(AbsBaseRunSpace original, ExpressionValue expressionValue, int param2
            , AbsBaseTrace absBaseTrace) {
        return expressionValue.getAbsBaseRunSpace();
    }

    @Override
    public ExpressionValue ToExpressionResult(AbsBaseRunSpace value, int param1, AbsBaseTrace absBaseTrace) {
        return ExpressionValue.from(value);
    }

    @Override
    public String FormattedValue(IUnit unit, AbsBaseRunSpace value, int param2, AbsBaseTrace absBaseTrace) {
        if (value == null) {
            return "[None]";
        }
        return value.Name();
    }

    @Override
    public AbsBaseRunSpace GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObject) {
        return null;
    }

    public boolean Equals(AbsBaseRunSpace first, AbsBaseRunSpace other) {
        return first == other;
    }

    public int GetHashCode(AbsBaseRunSpace absBaseRunSpace) {
        return absBaseRunSpace.hashCode();
    }

}
