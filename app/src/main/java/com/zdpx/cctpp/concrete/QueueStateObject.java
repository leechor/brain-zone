package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.StateFunction;
import com.zdpx.cctpp.annotations.StateFunctionReferenceReturnType;
import com.zdpx.cctpp.annotations.UnitClass;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.enu.IEnumMask;
import com.zdpx.cctpp.enu.NumericDataType;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.simioEnums.QueueRanking;

/**
 *
 */
public class QueueStateObject<T> extends AbsQueuePropertyObject {

    public QueueRanking queueRanking;
    private int flag;

    public QueueStateObject(String visitRequestQueue) {
        super(visitRequestQueue, true, true);
        this.numericDataType = NumericDataType.Real;

    }

    public QueueStateObject(String name, boolean isReadOnly, boolean IsPrivate) {
        super(name, isReadOnly, IsPrivate);
        this.numericDataType = NumericDataType.Real;
    }

    @Override
    public boolean CanInsert() {
        return false;
    }

    @Override
    public boolean CanInsert(boolean value) {
        return false;
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new QueueStateGridItemPropertyObject<T>(this, stateIGridItemPropertyObjectList);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.QueueState_ClassName;
    }


    @Override
    public String GetGridObjectDescription() {
        return EngineResources.QueueState_ClassDescription;
    }

    @Override
    public int IconIndex() {
        return 5;
    }

    @Override
    protected boolean SupportsDimensions() {
        return false;
    }

    @Override
    protected boolean SupportsUnitType() {
        return false;
    }

    private boolean contain(Flag flag) {
        return IEnumMask.contains(this.flag, flag);
    }

    private void update(Flag flag, boolean value) {
        if (value) {
            this.flag = IEnumMask.add(this.flag, flag);
        } else {
            this.flag = IEnumMask.remove(this.flag, flag);
        }
    }

    public boolean CanRemove() {
        return this.contain(Flag.Remove);
    }

    public void CanRemove(boolean value) {
        this.update(Flag.Remove, value);
    }

    @StateFunction(NameOverride = "NumberWaiting",
            Description = "Returns the current number of items  waiting in the queue.")
    public static double NumberWaiting(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).NumberInQueue();
    }

    @StateFunction(NameOverride = "MinimumNumberWaiting",
            Description = "Returns the minimum number of items that  concurrently waited in the queue.")
    public static double MinimumNumberWaiting(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MinimumNumberWaiting();
    }

    @StateFunction(NameOverride = "MaximumNumberWaiting",
            Description = "Returns the maximum number of items that concurrently waited in the queue .")
    public static double smethod_21(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MaximumNumberWaiting();
    }

    @StateFunction(NameOverride = "AverageNumberWaiting",
            Description = "Returns the average number of items that concurrently waited in the queue .")
    public static double smethod_22(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).AverageNumberWaiting();
    }

    @UnitClass(UnitType.Time)
    @StateFunction(NameOverride = "MinimumTimeWaiting",
            Description = "Returns the minimum time that an item waited in the queue (in hours).")
    public static double smethod_23(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MinimumTimeWaiting();
    }

    @UnitClass(UnitType.Time)
    @StateFunction(NameOverride = "MaximumTimeWaiting",
            Description = "Returns the maximum time that an item waited  in the queue (in hours).")
    public static double smethod_24(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MaximumTimeWaiting();
    }

    @StateFunction(NameOverride = "AverageTimeWaiting",
            Description = "Returns the average time that an item waited  in the queue (in hours).")
    @UnitClass(UnitType.Time)
    public static double smethod_25(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).AverageTimeWaiting();
    }

    @StateFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @StateFunction(NameOverride = "FirstItem",
            Description = "Returns a reference to the item ranked first in the  queue.")
    public static ExpressionValue smethod_26(AbsBaseTrace absBaseTrace) {
        BaseQueueGridItemTrace<?> baseTrace = (BaseQueueGridItemTrace<?>) absBaseTrace;
        if (baseTrace.NumberInQueue() > 0) {
            return ExpressionValue.from(baseTrace.getQueueList().getBehindQueue().getAssociatedObjectRunSpace());
        }
        return null;
    }

    @StateFunction(NameOverride = "LastItem",
            Description = "Returns a reference to the item ranked last in the queue.")
    @StateFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue smethod_27(AbsBaseTrace absBaseTrace) {
        BaseQueueGridItemTrace<?> baseTrace = (BaseQueueGridItemTrace<?>) absBaseTrace;
        if (baseTrace.NumberInQueue() > 0) {
            return ExpressionValue.from(baseTrace.getQueueList().getAheadQueue().getAssociatedObjectRunSpace());
        }
        return null;
    }

    @StateFunction(NameOverride = "ItemAtIndex", Arguments = "index",
            Description = "Returns a reference to the item ranked at a specified index position in the queue.")
    @StateFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue smethod_28(AbsBaseTrace absBaseTrace, ExpressionValue[] expressionValues) {
        BaseQueueGridItemTrace<?> baseQueueGridItemTrace = (BaseQueueGridItemTrace<?>) absBaseTrace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    absBaseTrace.getName(), "ItemAtIndex"));
        }
        Queue<?> queue = baseQueueGridItemTrace.getQueueList().ItemAtIndex(num - 1);
        if (queue != null) {
            return ExpressionValue.from(queue.getAssociatedObjectRunSpace());
        }
        return null;
    }

    @StateFunction(NameOverride = "IndexOfItem", Arguments = "object",
            Description = "Returns the one-based rank position of a  specified object in the queue. If the queue does" +
                    " not contain the object then the value 0 is returned.")
    public static double smethod_29(AbsBaseTrace absBaseTrace, ExpressionValue[] param1) {
        BaseQueueGridItemTrace<?> baseTrace = (BaseQueueGridItemTrace<?>) absBaseTrace;
        IntelligentObjectRunSpace intelligentObjectRunSpace = null;
        try {
            intelligentObjectRunSpace = (IntelligentObjectRunSpace) param1[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (intelligentObjectRunSpace == null) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    absBaseTrace.getName(), "IndexOfItem"));
        }
        return (double) baseTrace.IndexOf(intelligentObjectRunSpace);
    }

    @UnitClass(UnitType.Currency)
    @StateFunction(NameOverride = "MinimumCostWaiting", Description = "Returns the minimum cost that an item accrued " +
            "while it waited in the queue.")
    public static double smethod_30(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MinimumCostWaiting();
    }

    @UnitClass(UnitType.Currency)
    @StateFunction(NameOverride = "MaximumCostWaiting", Description = "Returns the maximum cost that an item accrued " +
            "while it waited in the queue.")
    public static double smethod_31(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).MaximumCostWaiting();
    }

    @StateFunction(NameOverride = "AverageCostWaiting", Description = "Returns the average cost that an item accrued " +
            "while it waited in the queue.")
    @UnitClass(UnitType.Currency)
    public static double smethod_32(AbsBaseTrace absBaseTrace) {
        return ((BaseQueueGridItemTrace<?>) absBaseTrace).AverageCostWaiting();
    }

    @StateFunction(NameOverride = "Contains", Arguments = "object",
            Description = "Returns True (1) if the queue contains the specified object. Otherwise, the value False " +
                    "(0) is returned.")
    public static double smethod_33(AbsBaseTrace absBaseTrace, ExpressionValue[] param1) {
        BaseQueueGridItemTrace<?> baseTrace = (BaseQueueGridItemTrace<?>) absBaseTrace;
        IntelligentObjectRunSpace intelligentObjectRunSpace = null;
        try {
            intelligentObjectRunSpace = (IntelligentObjectRunSpace) param1[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (intelligentObjectRunSpace == null) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    absBaseTrace.getName(), "Contains"));
        }
        if (baseTrace.Contains(intelligentObjectRunSpace)) {
            return 1.0;
        }
        return 0.0;
    }

    private enum Flag implements IEnumMask {
        None,
        Insert,
        Remove;

        Flag() {
            this.mask = (1 << ordinal());
        }

        private final int mask;

        public final int mask() {
            return this.mask;
        }
    }
}
