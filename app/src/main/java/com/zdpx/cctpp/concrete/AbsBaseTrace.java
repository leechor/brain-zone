package com.zdpx.cctpp.concrete;


import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum34;
import com.zdpx.cctpp.enu.Enum42;
import com.zdpx.cctpp.utils.simu.IAboutReport;
import com.zdpx.cctpp.utils.simu.ITrace;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public abstract class AbsBaseTrace implements ITrace {

    protected final BaseStateIGridItemPropertyObject BaseSomeIGridItemProperties;
    protected final AbsBaseRunSpace AbsBaseRunSpace;

    public AbsBaseTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                        AbsBaseRunSpace absBaseRunSpace) {
        this.BaseSomeIGridItemProperties = baseSomeIGridItemProperties;
        this.AbsBaseRunSpace = absBaseRunSpace;
    }

    public BaseStateIGridItemPropertyObject getBaseSomeIGridItemProperties() {
        return this.BaseSomeIGridItemProperties;
    }


    public AbsBaseRunSpace getAbsBaseRunSpace() {
        return this.AbsBaseRunSpace;
    }

    public void InitializeState() {
        this.InitializeStateImpl(Enum23.One);
    }

    abstract void InitializeStateImpl(AbsBaseTrace.Enum23 enum23);

    public void InitializeStatistics() {
    }

    public void ClearState(Enum34 enum34) {
        this.InitializeStateImpl(Enum23.One);
    }

    public void GetReportedStatistics(IAboutReport aboutReport) {
    }

    protected abstract void CopyFromCore(AbsBaseTrace absBaseTrace);

    public abstract ExpressionValue ExpressionValue();

    public abstract void ExpressionValue(ExpressionValue value);

    public abstract Object TableValue(IUnit unit);

    public abstract void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue);

    public abstract String FormattedValue(IUnit unit);

    public ExpressionValue GetValueAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1) {
        return ExpressionValue.Instance;
    }

    public void SetValueAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1, ExpressionValue expressionValue) {
    }

    public void SetValueWithoutRunSpaceNotificationsAt(AbsBaseTrace.CapacityWrapper capacityWrapper, int param1,
                                                       ExpressionValue param2) {
    }

    public ExpressionValue GetParameter(int param0) {
        return ExpressionValue.Instance;
    }

    public void SetParameter(int param0, ExpressionValue expressionValue) {
    }

    public String FormattedParameterValue(IUnit unit, int param1) {
        return null;
    }

    protected Double GetStateSnapshotValue() {
        return this.ExpressionValue().getExpressionResult();
    }

    @Override
    public boolean TraceFlag() {
        return false;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    @Override
    public void traceMethod(TokenRunSpace tokenRunSpace, Enum42 enum42, String param2) {
        this.traceMethod(this.AbsBaseRunSpace.getSomeRun().TimeNow(), tokenRunSpace, enum42, param2);
    }

    @Override
    public void traceMethod(double param0, TokenRunSpace tokenRunSpace, Enum42 enum42, String param3) {
        this.AbsBaseRunSpace.traceMethod(param0, tokenRunSpace, enum42, param3);
    }

    @Override
    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      TokenRunSpace tokenRunSpace, String param3, String param4, String param5) {
        this.runtimeWarningHandler(intelligentObjectRunSpace, warning, this.AbsBaseRunSpace.getSomeRun().TimeNow(),
                tokenRunSpace, param3, param4, param5);
    }

    @Override
    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      double param2, TokenRunSpace tokenRunSpace, String param4, String param5,
                                      String param6) {
        this.runtimeWarningHandler(intelligentObjectRunSpace, warning, param2, tokenRunSpace, param4, param5, param6);
    }

    public String DisplayName() {
        return this.getBaseSomeIGridItemProperties().StatePropertyObject.Name();
    }

    public String getName() {
        if (this.AbsBaseRunSpace == null || this.AbsBaseRunSpace.parentEmpty()) {
            return this.DisplayName();
        }
        return this.AbsBaseRunSpace.HierarchicalDisplayName() + "." + this.DisplayName();
    }

    protected void method_7(Enum24 enum24) {
        this.vmethod_2(enum24);
    }

    private void vmethod_2(Enum24 enum24) {
        // TODO: 2022/1/18    
    }

    public void method_3(AbsBaseTrace returnTable) {
        // TODO: 2022/2/7 
    }

    public abstract ExpressionValue InitialValue();

    public enum Enum23 {
        Zero,
        One
    }

    public enum Enum24 {
        Zero,
        One,
        Double
    }

    public class CapacityWrapper {
        public CapacityWrapper(int capacity) {
            this.capacity = capacity;
        }

        public final int capacity;
    }
}
