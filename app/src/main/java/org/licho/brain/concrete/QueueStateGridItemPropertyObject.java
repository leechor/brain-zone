package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class QueueStateGridItemPropertyObject<T> extends QueueGridItemPropertyObject {
    public QueueStateGridItemPropertyObject(QueueStateObject<T> tQueueState, StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        super(tQueueState, stateIGridItemPropertyObjectList);
        this.bool_0 = false;
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        return new BaseQueueGridItemTrace<T>(this, absBaseRunSpace);
    }
}
