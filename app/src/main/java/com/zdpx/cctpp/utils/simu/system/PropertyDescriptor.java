package com.zdpx.cctpp.utils.simu.system;

import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;

/**
 *
 */
public abstract class PropertyDescriptor extends java.beans.PropertyDescriptor {
    private Annotation[] annotations;

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public PropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {
        super(propertyName, beanClass);
    }

    public PropertyDescriptor(String propertyName, Annotation[] annotations, Class<?> beanClass) throws IntrospectionException {
        super(propertyName, beanClass);
        this.annotations = annotations;
    }

    public static Class<?> getComponentType() {
        return null;
    }

    public String Name() {
        return "";
    }

    public abstract Class<?> ComponentType();

    public abstract Class<?> PropertyType();

    public abstract boolean IsReadOnly();

    public abstract Object GetValue(Object param0);

    public abstract void SetValue(Object target, Object param1);

    public abstract boolean CanResetValue(Object param0);

    public abstract void ResetValue(Object param0);

    public abstract boolean ShouldSerializeValue(Object param0);
}
