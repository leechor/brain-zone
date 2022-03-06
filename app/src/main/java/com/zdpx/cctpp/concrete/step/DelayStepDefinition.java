package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.annotations.StepCategory;
import com.zdpx.cctpp.concrete.AbsBaseStepDefinition;
import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.ExpressionPropertyDefinition;
import com.zdpx.cctpp.concrete.NumericDataPropertyDefinition;
import com.zdpx.cctpp.concrete.Resources;

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
