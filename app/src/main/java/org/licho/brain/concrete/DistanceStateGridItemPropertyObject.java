package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class DistanceStateGridItemPropertyObject extends CostStateGridItemPropertyObject {
    public DistanceStateGridItemPropertyObject(CostStatePropertyObject stateProperty,
                                               org.licho.brain.concrete.StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Distance(this, absBaseRunSpace);
    }
}
