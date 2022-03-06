package com.zdpx.cctpp.utils.simu.system;

import com.zdpx.cctpp.concrete.PropertyDescriptorCollection;

import java.beans.PropertyDescriptor;

/**
 *
 */
public interface ITypedList {
    String GetListName(PropertyDescriptor[] listAccessors);

    PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors);
}
