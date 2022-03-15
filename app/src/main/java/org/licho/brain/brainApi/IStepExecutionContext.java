package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.ExitType;

public interface IStepExecutionContext {

    IElementData AssociatedObject();
    double ReturnValue();
    void ReturnValue(double value);
    void ProceedOut(ExitType exit);
}
