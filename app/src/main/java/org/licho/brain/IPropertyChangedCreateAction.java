package org.licho.brain;

import org.licho.brain.concrete.Guid;
import org.licho.brain.concrete.IGridObject;
import org.licho.brain.concrete.INotifyPropertyChanged;
import org.licho.brain.resource.Image;

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
