package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.simioEnums.ValidObjectType;

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
