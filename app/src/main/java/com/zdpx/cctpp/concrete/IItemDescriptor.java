package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.resource.Image;

/**
 *
 */
public interface IItemDescriptor {
    Object Item();

    String Name();

    String Group();

    int GroupImportance();

    String DisplayName();

    String ObjectType();

    String Category();

    int IconIndex();

    int StateIconIndex();

    Image Icon();

    void Rename(String newName);

    boolean CanRenameTo(String newName, StringBuffer failureReason);
}
