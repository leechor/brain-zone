package com.zdpx.cctpp.fake;

import com.zdpx.cctpp.concrete.PropertyDescriptorCollection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 *
 */
public class TypeDescriptor {

    public static PropertyDescriptorCollection GetProperties(Class<?> cl) {
        if (cl == null) {
            return null;
        }

        PropertyDescriptorCollection properties = new PropertyDescriptorCollection(null);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cl);
            PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
            properties.addAll(List.of(proDescrtptors));
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
