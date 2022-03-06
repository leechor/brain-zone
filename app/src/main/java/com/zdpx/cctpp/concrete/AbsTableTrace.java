package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public abstract class AbsTableTrace<T, TRAITS extends IExpressionConverter<T>> extends AbsBaseTableTrace<T, TRAITS> {
    public AbsTableTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    protected boolean InitializeParameters() {
        return true;
    }

    public abstract Object TableValue(IUnit unit);

    @Override
    public ExpressionValue GetParameter(int param0) {
        TRAITS traits = (TRAITS) AbsBaseTableTrace.getInstance(null);
        return traits.ToExpressionResult(this.initialValue, param0, this);
    }


    @Override
    public void SetParameter(int param0, ExpressionValue expressionValue) {
//        this.method_20(param0, expressionValue.getExpressionResult());
    }

    @Override
    public String FormattedParameterValue(IUnit param0, int param1) {
        TRAITS traits = (TRAITS) AbsBaseTableTrace.getInstance(null);
        return traits.FormattedValue(param0, this.initialValue, param1, this);
    }


}
