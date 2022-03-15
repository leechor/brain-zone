package org.licho.brain.concrete;

/**
 *
 */
public class VolumeUnitPropertyObject extends CostStatePropertyObject {
    public VolumeUnitPropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new VolumeUnitGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }
}
