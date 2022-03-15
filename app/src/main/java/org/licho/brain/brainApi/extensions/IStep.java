package org.licho.brain.brainApi.extensions;

import org.licho.brain.brainApi.IStepExecutionContext;
import org.licho.brain.brainApi.enu.ExitType;


public interface IStep {
ExitType Execute(IStepExecutionContext context);
}
