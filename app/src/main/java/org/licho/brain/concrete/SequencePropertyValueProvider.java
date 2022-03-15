package org.licho.brain.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SequencePropertyValueProvider implements IValueProvider {
    private String value;

    public SequencePropertyValueProvider(String value) {
        this.value = value;

    }

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {

        if (intelligentObjectDefinition == null) {
            return null;
        }
        List<String> names = new ArrayList<>();
        for (Table table : intelligentObjectDefinition.Tables().values) {
            if (table.Schema().getIndex() != -1) {
                names.add(table.Name());
            }
        }
        names.sort(String::compareTo);

        List<String> values =
                new ArrayList<>(intelligentObjectDefinition.filterNameByStringPropertyDefinition(SequencePropertyDefinition.class::isInstance));
        values.sort(String::compareTo);
        names.addAll(values);
        if (this.value != null) {
            names.add(0, this.value);
        }
        return names.toArray(new String[0]);
    }
}
