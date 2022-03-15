package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class MovementStateGridItemPropertyObject extends DistanceStateGridItemPropertyObject {
    public MovementStateGridItemPropertyObject(MovementStatePropertyObject statePropertyObject,
                                               org.licho.brain.concrete.StateIGridItemPropertyObjectList propertyObjectList) {
        super(statePropertyObject, propertyObjectList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Movement(this, absBaseRunSpace);
    }
}
