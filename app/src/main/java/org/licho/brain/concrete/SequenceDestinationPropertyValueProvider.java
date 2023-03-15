package org.licho.brain.concrete;

import org.licho.brain.concrete.node.Node;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SequenceDestinationPropertyValueProvider implements IValueProvider {
    private String name;
    private boolean acceptsAnyNode;

    public SequenceDestinationPropertyValueProvider(String name, boolean acceptsAnyNode) {
        this.name = name;
        this.acceptsAnyNode = acceptsAnyNode;
    }

    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= intelligentObjectDefinition.nodes.size(); i++) {
            Node node = intelligentObjectDefinition.nodes.get(i - 1);
            if (this.acceptsAnyNode) {
                list.add(node.InstanceName());
            } else if (node.IntelligentObject != null && node.NodeClassProperty.InputLocationType() != NodeInputLogicType.None) {
                IntelligentObjectDefinition objectDefinition =
                        (IntelligentObjectDefinition) node.IntelligentObject.assignerDefinition;
                if (objectDefinition.haveOneTransferPoints()) {
                    list.add(node.IntelligentObject.InstanceName());
                } else {
                    list.add(node.InstanceName());
                }
            }
        }
        list.sort(String::compareTo);
        if (this.name != null) {
            list.add(0, this.name);
        }
        return list.toArray(new String[0]);
    }
}
