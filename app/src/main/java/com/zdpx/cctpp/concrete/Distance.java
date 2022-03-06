package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class Distance extends AbsTableTrace<MovementInfo, MovementInfoExpressionConverter> {
    public Distance(DistanceStateGridItemPropertyObject baseStateIGridItemPropertyObject,
                    com.zdpx.cctpp.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseStateIGridItemPropertyObject, absBaseRunSpace);
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }
}
