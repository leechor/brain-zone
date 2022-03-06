package com.zdpx.cctpp.concrete;

/**
 *
 */
public abstract class AbsQueue {
    public abstract void RemoveFromParentQueueStateRunSpace();

    abstract void NotifyAssociatedObjectDestroying();

    abstract int AssociatedObjectQueueItemListIndex();

    abstract double CostValue();

    abstract void CostValue(double value);
}
