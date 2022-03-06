package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class Movement extends AbsTableTrace<MovementState, MayRouteExpressionConverter> {

    public Movement(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
        this.t = MovementState.class;
        this.traits = MayRouteExpressionConverter.class;
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {

    }

    @Override
    public String FormattedValue(IUnit unit) {
        return null;
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

    public double method_26() {
        return Double.NaN;
    }

    public double RateValue() {
        return this.initialValue.getRate(this.AbsBaseRunSpace.getSomeRun().TimeNow());
    }
}
