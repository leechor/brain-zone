package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.utils.simu.system.ITypedList;

import java.beans.PropertyDescriptor;

/**
 *
 */
public class Breakpoints extends BindingList<Breakpoint> implements ITypedList {
    private static PropertyDescriptorCollection propertyDescriptorCollection;

    @Override
    public String GetListName(PropertyDescriptor[] listAccessors) {
        return null;
    }

    @Override
    public PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors) {
        return Breakpoints.propertyDescriptorCollection;
    }
}
