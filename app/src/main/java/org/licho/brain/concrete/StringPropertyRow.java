package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.PropertyGridFeel;

/**
 *
 */
public class StringPropertyRow extends IntelligentObjectProperty {

    private RateTable rateTable;

    public StringPropertyRow(StringPropertyDefinition stringPropertyDefinition, Properties properties) {
        super(stringPropertyDefinition, properties);
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return new GridItemProperty(super.getStringPropertyDefinition().Name(),
                super.getStringPropertyDefinition().GetCategoryName(definitions),
                super.getStringPropertyDefinition().overRidePropertyIndex + 1000,
                this.StringValue(),
                super.getDefaultName(definitions),
                PropertyGridFeel.editlist,
                super.getStringPropertyDefinition().GetDisplayName(definitions),
                super.getStringPropertyDefinition().GetDescription(definitions),
                new SubPropertyOperator_0<>(String.class, this,
                        this::StringValue, this::StringValue, null,
                        this::GetCandidatePropertyReferences));
    }


    @Override
    public String CompileValue() {
        super.clear();
        this.rateTable = null;
        String text = super.formatName(super.getObjectName());
        if (super.isInvalidObjectValue(null)) {
            if (super.getStringPropertyDefinition().RequiredValue()) {
                return EngineResources.ErrorRequiredValueNotSpecified;
            }
            return null;
        }

        if (super.method_31(text, RateTablePropertyDefinition.class,  255)) {
            return null;
        }
        IntelligentObjectDefinition intelligentObjectDefinition =
                super.getExperimentConstraintsIntelligentObjectDefinition();
        // TODO: 2021/12/8 
//        for (RateTable rateTable : intelligentObjectDefinition.RateTables().smethod_11<RateTable>()) {
//            if (super.formatName(rateTable.Name()).equals(text)) {
//                this.rateTable = rateTable;
//                return null;
//            }
//        }
        return EngineResources.ErrorTheSpecifiedNameWasNotFound;
    }

    @Override
    public String StringValue() {
        if (this.rateTable != null) {
            return this.rateTable.Name();
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public Object GetObjectValue() {
        return this.rateTable;
    }

}
