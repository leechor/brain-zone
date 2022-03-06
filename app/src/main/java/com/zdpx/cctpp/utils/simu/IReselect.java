package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.IGridObject;
import com.zdpx.cctpp.event.EventHandler;

/**
 *
 */
public interface IReselect {
    EventHandler<IGridObject> Reselect = new EventHandler<>();
}
