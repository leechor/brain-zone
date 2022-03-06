package com.zdpx.cctpp;

import com.zdpx.cctpp.concrete.CurrentBreakpointView;
import com.zdpx.cctpp.concrete.CurrentErrorView;
import com.zdpx.cctpp.concrete.CurrentObjectListView;
import com.zdpx.cctpp.concrete.CurrentProfileView;
import com.zdpx.cctpp.concrete.CurrentSearchView;
import com.zdpx.cctpp.concrete.CurrentTraceView;
import com.zdpx.cctpp.concrete.CurrentWatchView;

/**
 *⁄
 */
public interface IView {
	void SetCurrentTraceView(CurrentTraceView param0);
	void ActivateTraceView();
	void DeactivateTraceView();
	void SetCurrentWatchView(CurrentWatchView param0);
	void ActivateWatchView();
	void DeactivateWatchView();
	void SetCurrentErrorView(CurrentErrorView param0);
	void ActivateErrorView();
	void DeactivateErrorView();
	void SetCurrentSearchView(CurrentSearchView param0);
	void ActivateSearchView();
	void DeactivateSearchView();
	void SetCurrentBreakpointView(CurrentBreakpointView param0);
	void ActivateBreakpointView();
	void DeactivateBreakpointView();
	void SetCurrentProfileView(CurrentProfileView param0);
	void SetCurrentObjectListView(CurrentObjectListView param0);
	void ActivateObjectListView();
	void DoPassiveNotification(Object param0, String param1);

}
