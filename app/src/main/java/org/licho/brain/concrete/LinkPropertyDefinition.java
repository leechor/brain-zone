package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.brainEnums.ValidObjectType;

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
    public String getObjectClassName() {
        return EngineResources.LinkProperty_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.LinkProperty_ClassDescription;
    }


    @Override
    public AbstractGridObjectDefinition ElementReferenceValueType()

    {
        return LinkDefinition.LinkFacility;
    }
}
