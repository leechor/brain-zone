package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.ExitType;

public interface IStepExecutionContext {

    IElementData AssociatedObject();
    double ReturnValue();
    void ReturnValue(double value);
    void ProceedOut(ExitType exit);
}
