package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class CostStateGridItemPropertyObject extends PhysicalCharacteristicsGridItemPropertyObject {
    public CostStateGridItemPropertyObject(CostStatePropertyObject stateProperty, StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Cost(this, absBaseRunSpace);
    }
}
