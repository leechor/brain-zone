package org.licho.brain.concrete;


import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

@PropertyDefinitionName("NodeClassProperty")
public class NodeClassPropertyDefinitionDefinition extends StringPropertyDefinition {
    public NodeClassPropertyDefinitionDefinition(String name) {
        super(name);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new NodeClassPropertyRow(this, properties);
    }


    @Override
    public String GetGridObjectClassName() {
        return EngineResources.NodeClassProperty_ClassName;
    }


    @Override
    public String GetGridObjectDescription() {
        return EngineResources.NodeClassProperty_ClassDescription;
    }

    // Token: 0x170011E2 RID: 4578

    @Override
    IValueProvider ValueProvider() {
        return new StringOtherValueProvider(super.NullNullString());
    }
}