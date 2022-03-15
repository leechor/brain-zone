package org.licho.brain.api;

import org.licho.brain.resource.Image;

/**
 *
 */
public interface IItemDescriptor {
    Object getItem();

    String getName();

    String Group();

    int GroupImportance();

    String DisplayName();

    String ObjectType();

    String Category();

    int IconIndex();

    int SateIconIndex();

    Image getIcon();

    String getSummary();

    void Rename(String newName);

    // TODO: 2021/11/5
    boolean canRenameTo(String newName, String failureReason);
}
