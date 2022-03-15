package org.licho.brain.concrete;

import java.util.List;

/**
 *
 */
public class SelectionRulePropertyValueProvider implements IValueProvider {
    private String value;

    public SelectionRulePropertyValueProvider(String value) {
        this.value = value;

    }

    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        List<String> list = SelectionRuleDefinition.getSelectionRuleDefinitionNames();
        list.sort(String::compareTo);
        if (this.value != null) {
            list.add(0, this.value);
        }
        return list.toArray(new String[0]);
    }
}
