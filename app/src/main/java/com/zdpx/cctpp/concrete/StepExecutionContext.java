package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseStepProperty;
import com.zdpx.cctpp.simuApi.IElementData;
import com.zdpx.cctpp.simuApi.IExecutionContext;
import com.zdpx.cctpp.simuApi.IStepExecutionContext;
import com.zdpx.cctpp.simuApi.enu.ExitType;

/**
 *
 */
public class StepExecutionContext extends ExecutionContext implements IExecutionContext, IStepExecutionContext {
    private boolean alreadyExit;
    private boolean calendarEventWaiting;

    public StepExecutionContext(TokenRunSpace tokenRunSpace, AbsBaseStepProperty absBaseStepProperty) {
        super(tokenRunSpace, tokenRunSpace.ParentObjectRunSpace, absBaseStepProperty, tokenRunSpace);
    }

    @Override
    public IElementData AssociatedObject() {
        return null;
    }

    @Override
    public double ReturnValue() {
        return 0;
    }

    @Override
    public void ReturnValue(double value) {

    }

    @Override
    public void ProceedOut(ExitType exit) {

    }

    public boolean isAlreadyExit() {
		return this.alreadyExit;
    }

    public void setAlreadyExit(boolean value)
	{
		this.alreadyExit = value;
	}

    public boolean isCalendarEventWaiting() {
		return this.calendarEventWaiting;
    }
}
