package org.licho.brain.utils.simu;

import org.licho.brain.concrete.InOutputStream;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public interface IFilesStream extends IDisposable {
    IFiles StreamStore();

    // Token: 0x0600587A RID: 22650
    InOutputStream OpenMainStream();
}
