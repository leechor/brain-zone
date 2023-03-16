package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.utils.simu.IProjectOperator;

/**
 *
 */
public interface IItemView {
	ItemTypeEnum ItemType();
	int ViewType();
	Object HostView();
	boolean OpenIfClosed();
	boolean IsSubView();
	Object CreateViewIfNeeded(IProjectOperator param0);
	void ShowView(IProjectOperator param0);
	void CloseView(IProjectOperator project);
	void UpdateName(IProjectOperator param0, String param1);
	boolean CheckCloseView(Object param0);
	boolean RemoveViewOnClose();
	IViewInfo ViewUI();
}
