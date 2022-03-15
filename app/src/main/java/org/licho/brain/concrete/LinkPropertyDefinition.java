package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.simioEnums.ValidObjectType;

@PropertyDefinitionName("LinkProperty")
public class LinkPropertyDefinition extends ObjectInstancePropertyDefinition {


    public LinkPropertyDefinition(String name) {
        super(name, ValidObjectType.Link);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new LinkObjectInstancePropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.LinkProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.LinkProperty_ClassDescription;
    }


    @Override
    public AbsDefinition ElementReferenceValueType()

    {
        return LinkDefinition.LinkFacility;
    }
}
