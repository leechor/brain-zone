package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.resource.Image;

import java.util.List;

/**
 *
 */
public interface ILibrary {
    String Name();
    Image Icon();
    List<ILibraryInfo> Objects();
    boolean IsInternal();
}
