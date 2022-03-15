package org.licho.brain.concrete.step;

import org.licho.brain.annotations.StepCategory;
import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ExpressionPropertyDefinition;
import org.licho.brain.concrete.NumericDataPropertyDefinition;
import org.licho.brain.concrete.Resources;

@StepCategory(category="Common Steps")
public class DelayStepDefinition extends AbsBaseStepDefinition<DelayStepDefinition> {

	private DelayStepDefinition()
	{
          super("Delay");
		super.Description(Resources.StepDescription_Delay);
		ExpressionPropertyDefinition delayTime = new ExpressionPropertyDefinition("DelayTime");
		delayTime.DisplayName(Resources.Delay_DelayTime_DisplayName);
		delayTime.Description(Resources.Delay_DelayTime_Description);
		delayTime.DefaultString("0.0");
		delayTime.CategoryName(Resources.StepPropertyCategory_BasicLogic);
		delayTime.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
		super.getPropertyDefinitions().add(delayTime);
	}

	@Override
	public AbsPropertyObject CreateInstance(String name)
	{
		return new DelayStepProperty(DelayStepDefinition.class, name);
	}

	@Override
	protected  boolean isInterruptible()
	{
		return true;
	}

}
