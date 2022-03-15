package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public abstract class AbsTableTrace<T, TRAITS extends IExpressionConverter<T>> extends AbsBaseTableTrace<T, TRAITS> {
    public AbsTableTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    protected boolean InitializeParameters() {
        return true;
    }

    public abstract Object TableValue(IUnit unit);

    @Override
    public ExpressionValue GetParameter(int param0) {
        TRAITS traits = (TRAITS) getInstance(null);
        return traits.ToExpressionResult(this.initialValue, param0, this);
    }


    @Override
    public void SetParameter(int param0, ExpressionValue expressionValue) {
//        this.method_20(param0, expressionValue.getExpressionResult());
    }

    @Override
    public String FormattedParameterValue(IUnit param0, int param1) {
        TRAITS traits = (TRAITS) getInstance(null);
        return traits.FormattedValue(param0, this.initialValue, param1, this);
    }


}
