package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IBreakpoint;

/**
 *
 */
public abstract class AbstractStepProperty extends  AbsPropertyObject implements IOwner, IBreakpoint {
    public AbstractStepProperty(GridObjectDefinition definition, String name) {
        super(definition, name);
    }
}
