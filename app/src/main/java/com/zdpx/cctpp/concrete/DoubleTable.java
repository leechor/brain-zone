package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class DoubleTable extends AbsDoubleTableTrace<Double, DoubleExpressionConverter> {
    public DoubleTable(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, AbsBaseRunSpace absBaseRunSpace) {
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


    public double DoubleValue() {
        return this.initialValue;
    }

    public void DoubleValue(double value) {
        this.initialValue = value;
        super.method_7(Enum24.Double);
    }

    @Override
    protected double getCoreDoubleValue() {
        return 0;
    }



}
