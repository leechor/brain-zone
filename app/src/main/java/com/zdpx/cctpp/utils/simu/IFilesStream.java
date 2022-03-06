package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.InOutputStream;
import com.zdpx.cctpp.utils.simu.system.IDisposable;

/**
 *
 */
public interface IFilesStream extends IDisposable {
    IFiles StreamStore();

    // Token: 0x0600587A RID: 22650
    InOutputStream OpenMainStream();
}
