package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.concrete.AbsStepProperty;
import com.zdpx.cctpp.concrete.ExitPointPropertyRow;
import com.zdpx.cctpp.concrete.ExpressionPropertyRow;
import com.zdpx.cctpp.concrete.IntelligentObjectXml;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.entity.EnumPropertyRow;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.simioEnums.DecideType;

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
