package com.zdpx.cctpp.concrete;

import java.beans.IntrospectionException;

/**
 *
 */
public class GridObjectDefinitionPropertyDescriptor extends AbsSubPropertyDescriptor {
    protected static final  Class<?> type = GridObjectDefinition.class ;
    public GridObjectDefinitionPropertyDescriptor(StringPropertyDefinition stringPropertyDefinition) throws IntrospectionException {
        super(stringPropertyDefinition, type);
    }

    @Override
    protected Properties GetPropertyGroup(Object target) {
        if (target instanceof AbsPropertyObject) {
            AbsPropertyObject absPropertyObject = (AbsPropertyObject) target;
            return absPropertyObject.properties;
        }
        return null;
    }

    @Override
    public Class<?> ComponentType() {
        return type;
    }

}
