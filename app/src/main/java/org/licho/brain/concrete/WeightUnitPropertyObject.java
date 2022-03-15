package org.licho.brain.concrete;

/**
 *
 */
public class WeightUnitPropertyObject extends CostStatePropertyObject {
    public WeightUnitPropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new WeightUnitGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }
}
