package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.event.EventHandler;
import com.zdpx.cctpp.utils.simu.system.PropertyChangedEventArgs;
import com.zdpx.cctpp.utils.simu.system.PropertyChangedEventHandler;

/**
 *
 */
public interface INotifyPropertyChanged {
    EventHandler<PropertyChangedEventArgs> PropertyChanged = new EventHandler<>();
}
