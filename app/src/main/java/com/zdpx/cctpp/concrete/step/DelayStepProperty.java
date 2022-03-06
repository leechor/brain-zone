package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.concrete.AbsStepProperty;
import com.zdpx.cctpp.concrete.ExpressionPropertyRow;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.property.AbsBaseStepProperty;

import java.util.Objects;

/**
 *
 */
public class DelayStepProperty extends AbsStepProperty<DelayStepDefinition> {
    private ExpressionPropertyRow delayTime;

    public DelayStepProperty(Class<DelayStepDefinition> cl, String name) {
        super(cl, name);
    }

    protected void initProperties() {
        this.delayTime = (ExpressionPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "DelayTime"));
        super.initProperties();
    }

    public ExpressionPropertyRow getDelayTime() {
        return this.delayTime;
    }

    @Override
    protected AbsBaseStepProperty.Enum15 vmethod_5(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (this.getDelayTime().getDoubleValue() == 0.0) {
            return AbsBaseStepProperty.Enum15.One;
        }
        return super.vmethod_5(intelligentObjectRunSpace);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
// TODO: 2022/2/8 
    }
}
