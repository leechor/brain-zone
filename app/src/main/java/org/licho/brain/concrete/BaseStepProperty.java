package org.licho.brain.concrete;

import org.licho.brain.concrete.exception.SimioRuntimeException;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.api.enu.ExitType;
import org.licho.brain.api.extensions.IStep;

import java.text.MessageFormat;

/**
 *
 */
public class BaseStepProperty extends AbsBaseStepProperty {
    public Properties properties;
    private IStep step;

    public BaseStepProperty(StepDefinition definition, String name) {
        super(definition, name);
        this.init();
        if (definition.getStepDefinition().NumberOfExits() > 1) {
            this.SecondExitPointProperty = (ExitPointPropertyRow) this.properties.values.get(2);
        }

    }

    private void init() {
        if (this.step == null && this.assignerDefinition != null && ((StepDefinition) this.assignerDefinition).getStepDefinition() != null) {
            this.step = ((StepDefinition) this.assignerDefinition).getStepDefinition().CreateStep(this.properties);
        }
    }

    private IStep Step() {
        this.init();
        return this.step;
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
        ExitType exitType = ExitType.FirstExit;
        StepExecutionContext stepExecutionContext = new StepExecutionContext(tokenRunSpace, this);
        try
        {
            if (this.Step() != null)
            {
                exitType = this.Step().Execute(stepExecutionContext);
            }
        }
        catch (SimioRuntimeException ex)
        {
            throw ex;
        }
        catch (Exception ex2)
        {
            String message = MessageFormat.format("Exception from step '{0}' execution. Message: {1}\n\nStack Trace:\n{2}",
                    this.InstanceName(), ex2.toString(), ex2.getStackTrace());
//            throw new SimioRuntimeException(message);
        }
        this.exitExecute(exitType, tokenRunSpace, stepExecutionContext, Enum73.Zero);
    }


    private void exitExecute(ExitType exitType, TokenRunSpace tokenRunSpace, StepExecutionContext stepExecutionContext, Enum73 enum73)  {
        		if (exitType != ExitType.Wait)
		{
			if (stepExecutionContext.isAlreadyExit())
			{
//				throw new InvalidOperationException("Trying to proceed from a step that was already exited by the executing token.");
			}
			stepExecutionContext.setAlreadyExit(true);
			if (stepExecutionContext.isCalendarEventWaiting())
			{
				String message = MessageFormat.format("User-defined step '{0}' scheduled a calendar event but did not return" +
                        " ExitType.Wait.", this.InstanceName());
				throw new SimioRuntimeException(message);
			}
			switch (exitType)
			{
			case FirstExit:
				this.PrimaryExitPointProperty.processExit(tokenRunSpace);
				break;
			case AlternateExit:
				this.SecondExitPointProperty.processExit(tokenRunSpace);
				break;
			}
			if (enum73 == BaseStepProperty.Enum73.One)
			{
				tokenRunSpace.getProcessPropertyElementRunSpace().method_37(tokenRunSpace, 0);
			}
		}
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    @Override
    public void SetBreakpoint() {

    }

    @Override
    public BreakpointWrapper ClearBreakpoint() {
        return null;
    }

    @Override
    public void RestoreBreakpoint(BreakpointWrapper param0) {

    }

    @Override
    public void OnBreakpointChanged() {

    }

    @Override
    public Breakpoint Breakpoint() {
        return null;
    }

    @Override
    public String BreakpointLocation() {
        return null;
    }

    @Override
    public ActiveModel Model() {
        return null;
    }

    public enum Enum73 {
        Zero,
        One
    }
}
