package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.ActiveModel;
import com.zdpx.cctpp.concrete.Breakpoint;
import com.zdpx.cctpp.concrete.BreakpointWrapper;

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
