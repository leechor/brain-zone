package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;


/**
 *
 */
public class VolumeUnitGridItemPropertyObject extends CostStateGridItemPropertyObject {
    public VolumeUnitGridItemPropertyObject(VolumeUnitPropertyObject stateProperty, StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Volume(this, absBaseRunSpace);
    }
}
