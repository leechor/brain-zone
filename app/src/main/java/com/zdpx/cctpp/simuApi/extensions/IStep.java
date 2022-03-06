package com.zdpx.cctpp.simuApi.extensions;

import com.zdpx.cctpp.simuApi.IStepExecutionContext;
import com.zdpx.cctpp.simuApi.enu.ExitType;


public interface IStep {
ExitType Execute(IStepExecutionContext context);
}
