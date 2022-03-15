package org.licho.brain.api;

import org.licho.brain.api.enu.ExitType;

public interface IStepExecutionContext {

    IElementData AssociatedObject();
    double ReturnValue();
    void ReturnValue(double value);
    void ProceedOut(ExitType exit);
}
