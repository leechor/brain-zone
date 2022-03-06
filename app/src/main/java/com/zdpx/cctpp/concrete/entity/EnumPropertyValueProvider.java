package com.zdpx.cctpp.concrete.entity;

import com.zdpx.cctpp.concrete.IValueProvider;
import com.zdpx.cctpp.concrete.IntelligentObjectDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class EnumPropertyValueProvider implements IValueProvider {
    private EnumPropertyDefinition enumPropertyDefinition;

    public EnumPropertyValueProvider(EnumPropertyDefinition enumPropertyDefinition) {
        this.enumPropertyDefinition = enumPropertyDefinition;
    }

    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.enumPropertyDefinition.stringValues.length; i++) {
            if (this.enumPropertyDefinition.visibles[i]) {
                list.add(this.enumPropertyDefinition.stringValues[i]);
            }
        }
        return list.toArray(new String[0]);
    }
}
