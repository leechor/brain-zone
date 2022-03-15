package org.licho.brain.concrete;

/**
 *
 */
public class MovementStatePropertyObject extends SecondOrderStatePropertyObject {
    public MovementStatePropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new MovementStateGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }
}
