package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class Movement extends AbsTableTrace<MovementState, MayRouteExpressionConverter> {

    public Movement(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
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
