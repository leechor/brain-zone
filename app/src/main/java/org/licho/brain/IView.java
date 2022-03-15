package org.licho.brain;

import org.licho.brain.concrete.CurrentBreakpointView;
import org.licho.brain.concrete.CurrentErrorView;
import org.licho.brain.concrete.CurrentObjectListView;
import org.licho.brain.concrete.CurrentProfileView;
import org.licho.brain.concrete.CurrentSearchView;
import org.licho.brain.concrete.CurrentTraceView;
import org.licho.brain.concrete.CurrentWatchView;

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
