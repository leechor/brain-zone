package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.utils.simu.IBreakpoint;

/**
 *
 */
public abstract class AbstractStepProperty extends  AbsPropertyObject implements IOwner, IBreakpoint {
    public AbstractStepProperty(GridObjectDefinition definition, String name) {
        super(definition, name);
    }
}
