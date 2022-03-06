package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class MovementStateGridItemPropertyObject extends DistanceStateGridItemPropertyObject {
    public MovementStateGridItemPropertyObject(MovementStatePropertyObject statePropertyObject,
                                               com.zdpx.cctpp.concrete.StateIGridItemPropertyObjectList propertyObjectList) {
        super(statePropertyObject, propertyObjectList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Movement(this, absBaseRunSpace);
    }
}
