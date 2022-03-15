package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IBreakpoint;

/**
 *
 */
public class BreakpointWrapper {

    private Breakpoint breakpoint;
    private IBreakpoint iBreakpoint;

    public BreakpointWrapper(IBreakpoint iBreakpoint, Breakpoint breakpoint) {
        this.iBreakpoint = iBreakpoint;
        this.breakpoint = breakpoint;
    }


    public IBreakpoint getiBreakpoint() {
        return iBreakpoint;
    }

    private void setiBreakpoint(IBreakpoint iBreakpoint) {
        this.iBreakpoint = iBreakpoint;
    }


    public Breakpoint Breakpoint() {
        return this.breakpoint;
    }

    private void Breakpoint(Breakpoint breakpoint) {
        this.breakpoint = breakpoint;
    }

    public IBreakpoint getIBreakpoint() {
		return this.iBreakpoint;
    }
}
