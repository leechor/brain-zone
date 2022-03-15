package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class Cost extends AbsTableTrace<Struct85, Class906> {
    {
        this.t = Struct85.class;
        this.traits = Class906.class;
    }

    public Cost(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {

    }


    public double RateValue() {
        return this.initialValue.Rate();
    }
}
