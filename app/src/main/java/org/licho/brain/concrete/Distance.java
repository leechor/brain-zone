package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class Distance extends AbsTableTrace<MovementInfo, MovementInfoExpressionConverter> {
    public Distance(DistanceStateGridItemPropertyObject baseStateIGridItemPropertyObject,
                    org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseStateIGridItemPropertyObject, absBaseRunSpace);
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }
}
