package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.PropertyGridFeel;

/**
 *
 */
public class SequencePropertyInTableRow extends IntelligentObjectProperty {
    private Table table1;

    public SequencePropertyInTableRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    private Table Table() {
        return this.table1;
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return new GridItemProperty(super.getStringPropertyDefinitionInfo().Name(),
                super.getStringPropertyDefinitionInfo().GetCategoryName(definitions),
                super.getStringPropertyDefinitionInfo().overRidePropertyIndex + 1000, this.StringValue(),
                super.getDefaultName(definitions), PropertyGridFeel.editlist,
                super.getStringPropertyDefinitionInfo().GetDisplayName(definitions),
                super.getStringPropertyDefinitionInfo().GetDescription(definitions),
                new SubPropertyOperator_0<>(String.class, this, this::StringValue, this::StringValue, null,
                        this::GetCandidatePropertyReferences));
    }

    @Override
    public Object GetNativeObject() {
        return this.StringValue();
    }

    @Override
    public String StringValue() {
        if (this.Table() != null) {
            return this.Table().Name();
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    public Object GetObjectValue() {
        return this.Table();
    }

    @Override
    public String CompileValue() {
        super.clear();
        this.table1 = null;
        String name = super.formatName(super.getObjectNameMaybe());
        if (super.isInvalidObjectNameValue(null)) {
            if (super.getStringPropertyDefinitionInfo().RequiredValue()) {
                return EngineResources.ErrorRequiredValueNotSpecified;
            }
            return null;
        } else {
            if (super.method_31(name, SequencePropertyDefinition.class, 255)) {
                return null;
            }
            if (name.contains(".")) {
                return EngineResources.ErrorInvalidSyntax;
            }
            IntelligentObjectDefinition intelligentObjectDefinition =
                    super.getExperimentConstraintsIntelligentObjectDefinition();
            Table table = intelligentObjectDefinition.Tables().getTableByName(name);
            if (table == null) {
                return EngineResources.ErrorTheSpecifiedNameWasNotFound;
            }
            if (table.Schema().getIndex() != -1) {
                this.table1 = table;
                super.setObjectValue(name);
                return null;
            }
            return EngineResources.CompileError_NoSequencePropertyInTable;
        }
    }

    @Override
    public void UpdateForParentObjectTableChange(Table table, Enum38 enum38) {
        if (this.Table() != null && table == this.Table() && enum38 == Enum38.Zero) {
            super.processPropertyChange();
            return;
        }
        super.UpdateForParentObjectTableChange(table, enum38);
    }

    @Override
    public void UpdateForParentObjectTableColumnChange(Table table, StringPropertyDefinition stringPropertyDefinition
            , Enum38 enum38) {
        if (this.Table() != null && table == this.Table() && enum38 == Enum38.Zero) {
            super.processPropertyChange();
            return;
        }
        super.UpdateForParentObjectTableColumnChange(table, stringPropertyDefinition, enum38);
    }

}
