package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.utils.simu.system.IDisposable;

/**
 *
 */
public interface IContextBound {
    void UpdateErrors();

    IDisposable LoadTransaction();

    void NotifyOtherContextBound(IContextBound contextBound);
}
