package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ExitPointPropertyDefinition;
import org.licho.brain.concrete.ExpressionPropertyDefinition;
import org.licho.brain.concrete.Resources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.brainEnums.DecideType;

/**
 *
 */
public class DecideStepDefinition extends AbsBaseStepDefinition<DecideStepDefinition> {

    private DecideStepDefinition() {
        super("Decide");
        super.Description(Resources.StepDescription_Decide);
        super.getExitPointPropertyDefinition().Name("True");
        super.getExitPointPropertyDefinition().DisplayName(Resources.Decide_TrueExit_DisplayName);
        super.getExitPointPropertyDefinition().Description(Resources.Decide_TrueExit_Description);
        ExitPointPropertyDefinition falseDefinition = new ExitPointPropertyDefinition("False");
        falseDefinition.DisplayName(Resources.Decide_FalseExit_DisplayName);
        falseDefinition.Description(Resources.Decide_FalseExit_Description);
        EnumPropertyDefinition decideType = new EnumPropertyDefinition("DecideType", DecideType.class);
        decideType.DisplayName(Resources.Decide_DecideType_DisplayName);
        decideType.Description(Resources.Decide_DecideType_Description);
        decideType.DefaultString(DecideType.ConditionBased.toString());
        decideType.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        ExpressionPropertyDefinition expression = new ExpressionPropertyDefinition("Expression");
        expression.DisplayName(Resources.Decide_Expression_DisplayName);
        expression.Description(Resources.Decide_Expression_Description);
        expression.DefaultString("0.0");
        expression.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        expression.RequiredValue(false);
        ExpressionPropertyDefinition randomNumberStream = new ExpressionPropertyDefinition("RandomNumberStream");
        randomNumberStream.DisplayName(Resources.Decide_RandomNumberStream_DisplayName);
        randomNumberStream.Description(Resources.Decide_RandomNumberStream_Description);
        randomNumberStream.DefaultString("0");
        randomNumberStream.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        randomNumberStream.SwitchNumericProperty(decideType);
        randomNumberStream.SwitchNumericValue(0.0);
        randomNumberStream.RequiredValue(false);
        randomNumberStream.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(falseDefinition);
        super.getPropertyDefinitions().add(decideType);
        super.getPropertyDefinitions().add(expression);
        super.getPropertyDefinitions().add(randomNumberStream);
        this.level = 3;
    }

	@Override
    public AbsPropertyObject    CreateInstance(String name) {
        return new DecideStepProperty(DecideStepDefinition.class, name);
    }

}
