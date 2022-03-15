package org.licho.brain.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BooleanValueProvider implements IValueProvider {
    private boolean bool_0;

    public BooleanValueProvider(boolean isInfinityInList) {
        this.bool_0 = isInfinityInList;
    }

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        List<String> list = new ArrayList<>();
        if (this.bool_0) {
            list.add(0, "Infinity");
        }
        return list.toArray(new String[0]);
    }
}
