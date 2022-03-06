package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class Cost extends AbsTableTrace<Struct85, Class906> {
    {
        this.t = Struct85.class;
        this.traits = Class906.class;
    }

    public Cost(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                com.zdpx.cctpp.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
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
