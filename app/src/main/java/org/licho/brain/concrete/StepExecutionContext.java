package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.api.IElementData;
import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.IStepExecutionContext;
import org.licho.brain.api.enu.ExitType;

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
