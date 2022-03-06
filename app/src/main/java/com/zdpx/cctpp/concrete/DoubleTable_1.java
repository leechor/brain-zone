package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class DoubleTable_1 extends AbsDoubleTableTrace<Struct121, Class1212> {
    public DoubleTable_1(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    @Override
    void InitializeStateImpl(Enum23 enum23) {

    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {

    }

    @Override
    public ExpressionValue ExpressionValue() {
        return null;
    }

    @Override
    public void ExpressionValue(ExpressionValue value) {

    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {

    }

    @Override
    public String FormattedValue(IUnit unit) {
        return null;
    }

    @Override
    protected double getCoreDoubleValue() {
        return 0;
    }

    public double DoubleValue() {
        if (this.initialValue.bool_0) {
            return Double.POSITIVE_INFINITY;
        }
        return this.method_20();
    }

    public int method_20() {
        if (this.initialValue.bool_0) {
            return Integer.MAX_VALUE;
        }
        return this.initialValue.int_0;
    }
}
