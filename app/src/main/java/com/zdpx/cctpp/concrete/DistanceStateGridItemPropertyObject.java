package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class DistanceStateGridItemPropertyObject extends CostStateGridItemPropertyObject {
    public DistanceStateGridItemPropertyObject(CostStatePropertyObject stateProperty,
                                               com.zdpx.cctpp.concrete.StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Distance(this, absBaseRunSpace);
    }
}
