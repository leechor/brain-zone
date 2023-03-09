package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.PropertyGridFeel;

/**
 *
 */
public class TokenClassPropertyRow extends IntelligentObjectProperty {
    private AbsDefinition absDefinition;

    public TokenClassPropertyRow(TokenClassPropertyDefinition tokenClassPropertyDefinition, Properties properties) {
        super(tokenClassPropertyDefinition, properties);
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return new GridItemProperty(super.getStringPropertyDefinition().Name(),
                super.getStringPropertyDefinition().GetCategoryName(definitions),
                super.getStringPropertyDefinition().overRidePropertyIndex + 1000, this.StringValue(),
                super.getDefaultName(definitions), PropertyGridFeel.list,
                super.getStringPropertyDefinition().GetDisplayName(definitions),
                super.getStringPropertyDefinition().GetDescription(definitions),
                new SubPropertyOperator_0<>(String.class, this, this::StringValue, this::StringValue));
    }

    @Override
    public String StringValue() {
        if (this.absDefinition != null) {
            return this.absDefinition.Name();
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public String CompileValue() {
        this.absDefinition = null;
        String objectName = super.formatName(super.getObjectName());
        if (super.isInvalidObjectValue(null)) {
            if (super.getStringPropertyDefinition().RequiredValue()) {
                return EngineResources.ErrorRequiredValueNotSpecified;
            }
            return null;
        } else {
            if (objectName.equalsIgnoreCase(TokenDefinition.objectName)) {
                this.absDefinition = TokenDefinition.Instance;
            }
            if (this.absDefinition == null) {
                this.absDefinition =
                        super.getExperimentConstraintsIntelligentObjectDefinition().getTokens().getTokenDefinitionByName(objectName);
            }
            if (this.absDefinition == null) {
                return EngineResources.ErrorTheSpecifiedNameWasNotFound;
            }
            return null;
        }
    }
}
