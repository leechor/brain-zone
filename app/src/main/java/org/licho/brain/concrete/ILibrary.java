package org.licho.brain.concrete;

import org.licho.brain.resource.Image;

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
