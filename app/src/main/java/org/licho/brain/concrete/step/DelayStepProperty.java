package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.property.AbsBaseStepProperty;

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
