package org.licho.brain.concrete;

import org.licho.brain.resource.Image;

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
