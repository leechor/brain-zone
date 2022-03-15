package org.licho.brain.concrete;

import org.licho.brain.event.EventHandler;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;

/**
 *
 */
public interface INotifyPropertyChanged {
    EventHandler<PropertyChangedEventArgs> PropertyChanged = new EventHandler<>();
}
