package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.resource.Image;

/**
 *
 */
public interface IItemDescriptor {
    Object getItem();

    String getName();

    String getGroup();

    int getGroupImportance();

    String getDisplayName();

    String getObjectType();

    String getCategory();

    int getIconIndex();

    int getSateIconIndex();

    Image getIcon();

    String getSummary();

    void rename(String newName);

    // TODO: 2021/11/5
    boolean canRenameTo(String newName, String failureReason);
}
