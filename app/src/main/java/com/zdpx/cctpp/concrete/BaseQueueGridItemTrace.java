package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class BaseQueueGridItemTrace<T> extends AbsQueueGridItemTrace {


    private QueueGridItemTraceInfo<T> queueGridItemTraceInfo;
    private QueueList<T> queueList;

    public BaseQueueGridItemTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                                  AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    @Override
    public boolean CanInsert() {
        return false;
    }

    @Override
    public boolean CanRemove() {
        return false;
    }

    @Override
    void InitializeStateImpl(Enum23 enum23) {

    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {

    }

    @Override
    public ExpressionValue ExpressionValue() {
        return ExpressionValue.from(this.queueList.Length());
    }

    @Override
    public void ExpressionValue(ExpressionValue value) {
        super.method_7(Enum24.Zero);
    }

    @Override
    public Object TableValue(IUnit unit) {
        return this.queueList.Length();
    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {

    }

    @Override
    public String FormattedValue(IUnit unit) {
        return String.valueOf(this.queueList.Length());
    }

    @Override
    public ExpressionValue InitialValue() {
			return ExpressionValue.from(0.0);
    }

    @Override
    public int NumberInQueue() {
        return this.queueList.Length();
    }

    public double MinimumNumberWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MinimumNumberWaiting();
        }
        return Double.NaN;
    }

    public double MaximumNumberWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MaximumNumberWaiting();
        }
        return Double.NaN;
    }

    public double AverageNumberWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.AverageNumberWaiting();
        }
        return Double.NaN;
    }

    public double MinimumTimeWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MinimumTimeWaiting();
        }
        return Double.NaN;
    }

    public double MaximumTimeWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MaximumTimeWaiting();
        }
        return Double.NaN;
    }

    public double AverageTimeWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.AverageTimeWaiting();
        }
        return Double.NaN;
    }

    public double MinimumCostWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MinimumCostWaiting();
        }
        return Double.NaN;
    }

    public double MaximumCostWaiting() {

        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.MaximumCostWaiting();
        }
        return Double.NaN;
    }

    public double AverageCostWaiting() {
        if (this.queueGridItemTraceInfo != null) {
            return this.queueGridItemTraceInfo.AverageCostWaiting();
        }
        return 0d;
    }

    public void remove(Queue<T> queueItem) {
        // TODO: 2021/12/1 
    }

    public QueueList<T> getQueueList() {
        return this.queueList;
    }

    @Override
    public int IndexOf(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (this.queueList != null) {
            int num = 1;
            for (Queue<T> behindQueue = this.queueList.getBehindQueue(); behindQueue != null; behindQueue =
                    behindQueue.getBehindQueue()) {
                if (behindQueue.getAssociatedObjectRunSpace() == intelligentObjectRunSpace) {
                    return num;
                }
                num++;
            }
        }
        return 0;
    }

    @Override
    public boolean Contains(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.IndexOf(intelligentObjectRunSpace) != 0;
    }

    @Override
    public void InsertQueueItem(AbsQueue param0, double param1) {

    }

    @Override
    public void InsertQueueItem(AbsQueue param0, double param1, IRunSpace param2, ExpressionPropertyRow expressionPropertyRow) {

    }

    @Override
    public IntelligentObjectRunSpace RemoveQueueItem(int param0) {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace RemoveQueueItem(int param0, boolean param1) {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace RemoveQueueItem(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace RemoveQueueItem(IntelligentObjectRunSpace intelligentObjectRunSpace,
                                                     boolean param1) {
        return null;
    }
}
