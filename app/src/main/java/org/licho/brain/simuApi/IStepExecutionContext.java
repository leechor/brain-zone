package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.ExitType;

public interface IStepExecutionContext {

    IElementData AssociatedObject();
    double ReturnValue();
    void ReturnValue(double value);
    void ProceedOut(ExitType exit);
}
