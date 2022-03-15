package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.brainEnums.ValidObjectType;

@PropertyDefinitionName("NodeProperty")
public class NodePropertyDefinition extends ObjectInstancePropertyDefinition {
    public NodePropertyDefinition(String name) {
        super(name, ValidObjectType.Node);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new NodeObjectInstancePropertyRow(this, properties);
    }

}
