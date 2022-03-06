package com.zdpx.cctpp.utils.simu;

import java.io.FileInputStream;

/**
 *
 */
public interface IFileRef {
    String Name();
    FileInputStream getStream();
}
