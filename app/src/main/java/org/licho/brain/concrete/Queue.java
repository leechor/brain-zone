package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IAssociatedObjectRunSpace;

/**
 *
 */
public class Queue<T> extends AbsQueue {
    private Queue<T> aheadQueue;
    private T currentEntry;
    private Queue<T> behindQueue;
    private int associatedObjectQueueItemListIndex = -1;
    private QueueList<T> queueList;
    private double value;
    private double double_0;
    private double costValue;

    Queue() {

    }

    public T current() {
        return this.currentEntry;
    }

    public QueueList<T> getQueueList() {
        return this.queueList;
    }

    public Queue<T> getAheadQueue() {
        return this.aheadQueue;
    }

    public Queue<T> getBehindQueue() {
        return this.behindQueue;
    }

    public void setAheadQueue(Queue<T> value) {
        this.aheadQueue = value;
    }

    public void setBehindQueue(Queue<T> value) {
        this.behindQueue = value;
    }

    void setCurrentEntry(T queueObject) {
        this.currentEntry = queueObject;
    }

    public IntelligentObjectRunSpace getAssociatedObjectRunSpace() {
        IAssociatedObjectRunSpace associatedObjectRunSpace = (IAssociatedObjectRunSpace) this.currentEntry;
        if (associatedObjectRunSpace != null) {
            return associatedObjectRunSpace.AssociatedObjectRunSpace();
        }
        return null;
    }

    void clear() {
        this.setBehindQueue(null);
        this.setAheadQueue(null);
        this.queueList = null;
    }

    void updateQueueList(QueueList<T> parentQueueList, double value) {
        this.queueList = parentQueueList;
        this.value = value;
    }

    public void init() {
        this.currentEntry = null;
        this.double_0 = 0.0;
        this.associatedObjectQueueItemListIndex = -1;
    }

    @Override
    public void RemoveFromParentQueueStateRunSpace() {
        if (this.queueList != null && this.queueList.getBaseQueueGridItemTrace() != null) {
            this.queueList.getBaseQueueGridItemTrace().remove(this);
        }
    }

    @Override
    void NotifyAssociatedObjectDestroying() {
        this.associatedObjectQueueItemListIndex = -1;
        this.RemoveFromParentQueueStateRunSpace();
    }

    @Override
    int AssociatedObjectQueueItemListIndex() {
        return this.associatedObjectQueueItemListIndex;
    }

    @Override
    double CostValue() {
        return this.costValue;
    }

    @Override
    void CostValue(double value) {
        this.costValue = value;
    }

    public double method_1() {
		return this.double_0;
    }

    public double getValue() {
        return this.value;
    }
}
