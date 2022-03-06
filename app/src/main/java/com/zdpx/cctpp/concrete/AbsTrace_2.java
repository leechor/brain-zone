package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public abstract class AbsTrace_2<T, TRAITS extends IExpressionConverter<T>> extends AbsBaseTrace {
    private CapacityBucket<T> CapacityBucket;
    private static SingleInstance instance = new SingleInstance();
    protected Class<T> t;
    protected Class<TRAITS> traits;

    public AbsTrace_2(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                      AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    @Override
    public ExpressionValue ExpressionValue() {
        return ExpressionValue.Instance;
    }

    @Override
    public void ExpressionValue(ExpressionValue value) {

    }

    protected ExpressionValue GetTableValueForArrayInitialization(IntelligentObjectProperty intelligentObjectProperty
            , IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return intelligentObjectProperty.getExpressionResult(null, intelligentObjectRunSpace).toExpressionValue();
    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {
        AbsTrace_2<T, TRAITS> baseTrace = (AbsTrace_2<T, TRAITS>) absBaseTrace;
        if (baseTrace.CapacityBucket != null) {
            if (this.CapacityBucket != null && this.CapacityBucket.Length() == baseTrace.CapacityBucket.Length()) {
                this.CapacityBucket.method_0(baseTrace.CapacityBucket);
                return;
            }
            this.CapacityBucket = new CapacityBucket<T>(baseTrace.CapacityBucket);
        }
    }

    @Override
    public void SetExpressionValueWithoutRunSpaceNotifications(ExpressionValue expressionValue) {
    }

    @Override
    public String FormattedValue(IUnit unit) {
        return null;
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }

    @Override
    public ExpressionValue InitialValue() {
        T initialValue = AbsTrace_2.instance.Instance(this.traits).GetInitialValue(this,
                (IntelligentObjectRunSpace) this.AbsBaseRunSpace);
        return AbsTrace_2.instance.Instance(this.traits).ToExpressionResult(initialValue, -1, this);
    }

}
