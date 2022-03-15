package org.licho.brain.api.extensions;

import org.licho.brain.api.IStepExecutionContext;
import org.licho.brain.api.enu.ExitType;


public interface IStep {
ExitType Execute(IStepExecutionContext context);
}
