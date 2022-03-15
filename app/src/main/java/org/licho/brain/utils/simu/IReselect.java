package org.licho.brain.utils.simu;

import org.licho.brain.concrete.IGridObject;
import org.licho.brain.event.EventHandler;

/**
 *
 */
public interface IReselect {
    EventHandler<IGridObject> Reselect = new EventHandler<>();
}
