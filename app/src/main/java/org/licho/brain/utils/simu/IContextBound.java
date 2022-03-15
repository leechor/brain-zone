package org.licho.brain.utils.simu;

import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public interface IContextBound {
    void UpdateErrors();

    IDisposable LoadTransaction();

    void NotifyOtherContextBound(IContextBound contextBound);
}
