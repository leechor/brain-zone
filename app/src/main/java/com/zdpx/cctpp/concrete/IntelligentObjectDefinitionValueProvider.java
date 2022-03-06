package com.zdpx.cctpp.concrete;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Predicate;


/**
 *
 */
public class IntelligentObjectDefinitionValueProvider implements IValueProvider {

    private static Predicate<StringPropertyDefinition> predicate;

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        List<String> values = Lists.newArrayList("False", "True");
        if (intelligentObjectDefinition != null) {
            if (predicate == null) {
                predicate = IntelligentObjectDefinitionValueProvider::isBooleanPropertyDefinition;
            }
//            List<String> list =
        }
        return new String[0];
    }

    private static boolean isBooleanPropertyDefinition(StringPropertyDefinition stringPropertyDefinition) {
        return stringPropertyDefinition instanceof BooleanPropertyDefinition;
    }
}
