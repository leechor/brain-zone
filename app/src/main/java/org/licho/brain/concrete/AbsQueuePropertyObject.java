package org.licho.brain.concrete;

/**
 *
 */
public abstract class AbsQueuePropertyObject extends BaseStatePropertyObject {
    public AbsQueuePropertyObject(String name, boolean isReadOnly, boolean IsPrivate) {
        super(name, isReadOnly, IsPrivate);
    }

    public abstract boolean CanInsert();

    public abstract boolean CanInsert(boolean value);

    public abstract boolean CanRemove();

    public abstract void CanRemove(boolean value);
}
