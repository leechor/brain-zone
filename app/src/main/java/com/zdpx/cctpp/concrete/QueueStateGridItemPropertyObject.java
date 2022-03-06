package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

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
