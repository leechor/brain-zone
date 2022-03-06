package com.zdpx.cctpp;

import com.zdpx.cctpp.concrete.Guid;
import com.zdpx.cctpp.concrete.IGridObject;
import com.zdpx.cctpp.concrete.INotifyPropertyChanged;
import com.zdpx.cctpp.event.EventHandler;
import com.zdpx.cctpp.resource.Image;

/**
 *
 */
public interface IPropertyChangedCreateAction extends INotifyPropertyChanged {
    	String Name();
	String Description();
	Image GetOrCreateThumbnail(Interface190 param0, int param1, int param2);
//	EventHandler<IPropertyChangedCreateAction> Changed;
	Guid UniqueID();
	IGridObject PropertyGridObject();
}
