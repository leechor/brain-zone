package com.zdpx.cctpp.concrete;



import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class PropertyDescriptorCollection extends ArrayList<PropertyDescriptor> {

    public PropertyDescriptorCollection(PropertyDescriptor[] properties) {
        if (properties == null) {
            return;
        }
        this.addAll(List.of(properties));
    }

}
