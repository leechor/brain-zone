package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;
import com.zdpx.cctpp.utils.simu.IProject;

/**
 *
 */
public interface IItemView {
	ItemTypeEnum ItemType();
	int ViewType();
	Object HostView();
	boolean OpenIfClosed();
	boolean IsSubView();
	Object CreateViewIfNeeded(IProject param0);
	void ShowView(IProject param0);
	void CloseView(IProject project);
	void UpdateName(IProject param0, String param1);
	boolean CheckCloseView(Object param0);
	boolean RemoveViewOnClose();
	IViewInfo ViewUI();
}
