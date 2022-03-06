package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.PropertyGridFeel;

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
        return new GridItemProperty(super.getStringPropertyDefinitionInfo().Name(),
                super.getStringPropertyDefinitionInfo().GetCategoryName(definitions),
                super.getStringPropertyDefinitionInfo().overRidePropertyIndex + 1000,
                this.StringValue(),
                super.getDefaultName(definitions),
                PropertyGridFeel.editlist,
                super.getStringPropertyDefinitionInfo().GetDisplayName(definitions),
                super.getStringPropertyDefinitionInfo().GetDescription(definitions),
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
            if (super.getStringPropertyDefinitionInfo().RequiredValue()) {
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
