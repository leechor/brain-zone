package org.licho.brain.simuApi.extensions;

import org.licho.brain.simuApi.IStepExecutionContext;
import org.licho.brain.simuApi.enu.ExitType;


public interface IStep {
ExitType Execute(IStepExecutionContext context);
}
