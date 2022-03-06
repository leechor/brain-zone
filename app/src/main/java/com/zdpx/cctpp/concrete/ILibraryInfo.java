package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.resource.Image;

/**
 *
 */
public interface ILibraryInfo {
    String Name();
    String Description();
    Image Icon();
    IntelligentObjectDefinition getIntelligentObjectDefinition();
    boolean isChild();
    String getId();

}
