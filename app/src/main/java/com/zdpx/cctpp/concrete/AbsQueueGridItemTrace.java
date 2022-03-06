package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public abstract class AbsQueueGridItemTrace extends AbsBaseTrace {
    public AbsQueueGridItemTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                                 AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    public abstract boolean CanInsert();
    public abstract boolean CanRemove();
    public abstract int NumberInQueue();

    public abstract int IndexOf(IntelligentObjectRunSpace intelligentObjectRunSpace);

    public abstract boolean Contains(IntelligentObjectRunSpace intelligentObjectRunSpace);

    public abstract void InsertQueueItem(AbsQueue param0, double param1);

	public abstract void InsertQueueItem(AbsQueue param0, double param1, IRunSpace param2, ExpressionPropertyRow expressionPropertyRow);

	public abstract IntelligentObjectRunSpace RemoveQueueItem(int param0);

	public abstract IntelligentObjectRunSpace RemoveQueueItem(int param0, boolean param1);

	public abstract IntelligentObjectRunSpace RemoveQueueItem(IntelligentObjectRunSpace intelligentObjectRunSpace);

	public abstract IntelligentObjectRunSpace RemoveQueueItem(IntelligentObjectRunSpace intelligentObjectRunSpace,
                                                              boolean param1);

}
