package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;

/**
 *
 */
public class NodeClassPropertyRow extends IntelligentObjectProperty {
    private NodeDefinition nodeFacility;

    public NodeClassPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    public NodeDefinition getNodeFacility() {
        return this.nodeFacility;
    }

    @Override
    public String StringValue() {
        if (this.nodeFacility == null) {
            return super.StringValue();
        }
        IntelligentObjectDefinition intelligentObjectDefinition = super.getExperimentConstraintsIntelligentObjectFacility();
        if (intelligentObjectDefinition != null) {
            return intelligentObjectDefinition.getInternalReference().Name(this.nodeFacility);
        }
        return this.nodeFacility.Name();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        StringPropertyDefinition definitionInfo = super.getStringPropertyDefinition();
//        return new GridItemProperty(definitionInfo.Name(),
//                definitionInfo.GetCategory(definitions),
//                definitionInfo.propertyIndex + 1000, StringValue(),
//                super.getDefaultName(definitions),
//                PropertyGridFeel.list,
//                definitionInfo.getDisplayName(definitions),
//                definitionInfo.getDescription(), )
        return null;
    }

    @Override
    public String CompileValue() {
        super.clear();
        this.nodeFacility = null;
        String text = super.formatName(super.getObjectName());
        // TODO: 2021/11/15
        return null;
    }

    @Override
    public void UpdateForParentObjectLibraryDefinitionChange(IntelligentObjectDefinition intelligentObjectDefinition,
                                                             Enum38 enu) {

    }

}
