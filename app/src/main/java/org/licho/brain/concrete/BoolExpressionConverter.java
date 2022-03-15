package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class BoolExpressionConverter implements IExpressionConverter<Boolean> {
    @Override
    public Boolean FromExpressionResult(Boolean original, ExpressionValue expressionValue, int param2,
                                        AbsBaseTrace absBaseTrace) {
        return null;
    }

    @Override
    public ExpressionValue ToExpressionResult(Boolean value, int param1, AbsBaseTrace absBaseTrace) {
		return ExpressionValue.from(Boolean.TRUE.equals(value) ? 1.0 : 0.0);
    }

    @Override
    public String FormattedValue(IUnit unit, Boolean value, int param2, AbsBaseTrace absBaseTrace) {
        if (Boolean.FALSE.equals(value))
		{
			return "False";
		}
		return "True";
    }

    @Override
    public Boolean GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
		return absBaseTrace.getBaseSomeIGridItemProperties().getValue(intelligentObjectRunSpace) == 1.0;
    }
}
