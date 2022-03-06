package com.zdpx.cctpp.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StringOtherValueProvider implements IValueProvider {
    private String target;

    public StringOtherValueProvider(String target) {
        this.target = target;
    }

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (InternalReference.NameIntelligentObjectDefinition nameIntelligentObjectDefinition :
                intelligentObjectDefinition.getInternalReference().allNameDefinitions()) {
            if (nameIntelligentObjectDefinition.Definition() instanceof NodeDefinition) {
                String name = nameIntelligentObjectDefinition.Definition().Name();
                if (!list.contains(name)) {
                    list.add(name);
                }
            }
        }

        List<NodeDefinition> enumerable =
                (List<NodeDefinition>) intelligentObjectDefinition.activeModel.projectDefinition.Libraries().ListOfAll();
        for (NodeDefinition nodeDefinition : enumerable) {
            String name = nodeDefinition.Name();
            if (!list.contains(name)) {
                list.add(name);
            }
        }

        for (StringPropertyDefinition stringPropertyDefinition :
                intelligentObjectDefinition.getPropertyDefinitions().values) {
            NodeClassPropertyDefinitionDefinition nodeClassPropertyDefinitionDefinition =
                    (NodeClassPropertyDefinitionDefinition) stringPropertyDefinition;
            if (nodeClassPropertyDefinitionDefinition != null) {
                list.add(nodeClassPropertyDefinitionDefinition.Name());
            }
        }

        list.sort(String::compareTo);
        if (this.target != null) {
            list.add(0, this.target);
        }
        return list.toArray(new String[0]);
    }
}
