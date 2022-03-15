package org.licho.brain.utils.simu;

import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.Breakpoint;
import org.licho.brain.concrete.BreakpointWrapper;

/**
 *
 */
public interface IBreakpoint {
	void SetBreakpoint();

	BreakpointWrapper ClearBreakpoint();

	void RestoreBreakpoint(BreakpointWrapper param0);

	void OnBreakpointChanged();

	Breakpoint Breakpoint();

	String BreakpointLocation();

	ActiveModel Model();
}
