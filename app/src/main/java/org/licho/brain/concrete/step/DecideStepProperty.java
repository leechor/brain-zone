package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.ExitPointPropertyRow;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.brainEnums.DecideType;

import java.util.Objects;

/**
 *
 */
public class DecideStepProperty extends AbsStepProperty<DecideStepDefinition> {
    private EnumPropertyRow decideType;
    private ExpressionPropertyRow expression;
    private ExpressionPropertyRow randomNumberStream;

    public DecideStepProperty(Class<DecideStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.SecondExitPointProperty = (ExitPointPropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                "False"));
        this.decideType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "False"));
        this.expression = (ExpressionPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "False"));
        this.randomNumberStream = (ExpressionPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "False"
        ));
        super.initProperties();
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {

    }

    public EnumPropertyRow getDecideType() {
        return this.decideType;
    }

    public ExpressionPropertyRow getExpression() {
        return this.expression;
    }

    public ExpressionPropertyRow getRandomNumberStream() {
        return this.randomNumberStream;
    }

    @Override
    public void LoadOldDefaultValuesForLoadFrom(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 70) {
            this.getDecideType().StringValue(DecideType.ProbabilityBased.toString());
        }
        super.LoadOldDefaultValuesForLoadFrom(intelligentObjectXml);
    }

    @Override
    public boolean ShouldAlwaysWriteOutPropertyValue(IntelligentObjectProperty intelligentObjectProperty) {
        return intelligentObjectProperty == this.getDecideType();
    }
}
