package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class WeightUnitGridItemPropertyObject extends CostStateGridItemPropertyObject {
    public WeightUnitGridItemPropertyObject(WeightUnitPropertyObject stateProperty, StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new Weight(this, absBaseRunSpace);
    }
}
