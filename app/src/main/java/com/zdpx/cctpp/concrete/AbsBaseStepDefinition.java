package com.zdpx.cctpp.concrete;

import jdk.dynalink.beans.StaticClass;

import java.lang.reflect.InvocationTargetException;
import java.rmi.activation.Activator;

/**
 *
 */
public class AbsBaseStepDefinition<T> extends AbsStepDefinition {
    public static SingleInstance definition = new SingleInstance();

    public AbsBaseStepDefinition(String name) {
        super(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public AbsStepDefinition DefaultDefinition(Class<?> clazz) {
        return (AbsStepDefinition)definition.Instance(clazz);
    }

}
