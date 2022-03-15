package org.licho.brain.utils.simu.system;

import org.licho.brain.concrete.PropertyDescriptorCollection;

import java.beans.PropertyDescriptor;

/**
 *
 */
public interface ITypedList {
    String GetListName(PropertyDescriptor[] listAccessors);

    PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors);
}
