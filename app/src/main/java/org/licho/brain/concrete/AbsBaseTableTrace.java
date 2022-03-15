package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.utils.simu.IAboutReport;
import org.licho.brain.utils.simu.IUnit;

import java.text.MessageFormat;
import java.util.Objects;

/**
 *
 */
public abstract class AbsBaseTableTrace<T, TRAITS extends IExpressionConverter<T>> extends AbsBaseTrace {
    protected T initialValue;
    private static SingleInstance instance = new SingleInstance();
    protected Class<T> t;
    protected Class<TRAITS> traits;

    public AbsBaseTableTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                             org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);

    }

    protected static IExpressionConverter getInstance(Class<?> t) {
        if (t != null) {
            return (IExpressionConverter) AbsBaseTableTrace.instance.Instance(t);
        }
        return null;
    }

    public abstract Object TableValue(IUnit unit);

    @Override
    public String FormattedValue(IUnit unit) {
//		return AbsBaseTableTrace < T,TRAITS >.instance.FormattedValue(unit, this.initialValue, -1, this);
        return null;
    }

    protected void method_7(Enum24 e) {

    }

    @Override
    void InitializeStateImpl(AbsBaseTrace.Enum23 enum23) {
        this.createInitialValue();
    }

    protected void createInitialValue() {
        this.initialValue = AbsBaseTableTrace.instance.Instance(this.traits)
                .GetInitialValue(this, this.getIntelligentObjectRunSpace());
    }

    @Override
    public void InitializeStatistics() {
    }

    @Override
    public void GetReportedStatistics(IAboutReport aboutReport) {
    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {
        AbsBaseTableTrace<T, TRAITS> absBaseTableTrace = (AbsBaseTableTrace<T, TRAITS>) absBaseTrace;
        this.initialValue = absBaseTableTrace.initialValue;
    }

    @Override
    public ExpressionValue ExpressionValue() {
        return AbsBaseTableTrace.instance.Instance(this.traits).ToExpressionResult(this.initialValue, -1, this);
    }

    @Override
    public void ExpressionValue(ExpressionValue value) {
        this.initialValue = AbsBaseTableTrace.instance.Instance(this.traits).FromExpressionResult(this.initialValue,
                value, -1, this);
        super.method_7(Enum24.Zero);
    }

    private IntelligentObjectRunSpace getIntelligentObjectRunSpace() {
        if (this.AbsBaseRunSpace instanceof IntelligentObjectRunSpace) {
            return (IntelligentObjectRunSpace) this.AbsBaseRunSpace;
        }
        return this.AbsBaseRunSpace.ParentObjectRunSpace;
    }


    @Override
    public ExpressionValue InitialValue() {
        T initialValue = AbsBaseTableTrace.instance.Instance(traits)
                .GetInitialValue(this, this.getIntelligentObjectRunSpace());
        return ((IExpressionConverter<T>) AbsBaseTableTrace.instance.Instance(this.getClass()))
                .ToExpressionResult(initialValue, -1, this);
    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {
        this.initialValue = AbsBaseTableTrace.instance.Instance(this.traits)
                .FromExpressionResult(this.initialValue, expressionValue, -1, this);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}: {1}", super.DisplayName(), this.initialValue);
    }

    public void setNewStateValue(T newStateValue) {
        if (!Objects.equals(newStateValue, this.initialValue)) {
            this.initialValue = newStateValue;
        }
    }

    @Override
    public ExpressionValue GetValueAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void SetValueAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1, ExpressionValue expressionValue) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void SetValueWithoutRunSpaceNotificationsAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1,
                                                       ExpressionValue param2) {
        throw new IndexOutOfBoundsException();
    }

}
