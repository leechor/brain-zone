package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;

import java.util.Map;

/**
 *
 */
public class TableDefinition extends GridObjectDefinition {
    private Schema parent;

    public TableDefinition() {
        super("Table");
        RepeatingPropertyDefinition repeatingPropertyDefinition = new RepeatingPropertyDefinition("TableData", this);
        super.getPropertyDefinitions().add(repeatingPropertyDefinition);
        repeatingPropertyDefinition.RequiredValue(false);
        repeatingPropertyDefinition.SetLocalVisible(Boolean.FALSE, super.getPropertyDefinitions());
    }

    public Schema Parent() {
        return this.parent;
    }

    public void Parent(Schema value) {
        this.parent = value;
    }

    public PropertyDefinitions Group() {
        return ((RepeatingPropertyDefinition) super.getPropertyDefinitions().values.get(0)).propertyDefinitions;
    }

    public void Group(PropertyDefinitions value) {

    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new TableProperty(this, name);
    }

    @Override
    public void NotifyPropertyDefinitionRenamed(StringPropertyDefinition stringPropertyDefinition, String param1) {

    }

    @Override
    public void NotifyPropertyInserted(PropertyDefinitions propertyDefinitions,
                                       StringPropertyDefinition stringPropertyDefinition, int param2,
                                       Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
    }

    @Override
    public void NotifyPropertyRemoved(PropertyDefinitions propertyDefinitions,
                                      StringPropertyDefinition stringPropertyDefinition, int param2,
                                      Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
        if (propertyDefinitions == ((RepeatingPropertyDefinition) (super.getPropertyDefinitions().values.get(0))).propertyDefinitions) {
            this.Parent().PropertyRemoved(stringPropertyDefinition);
        }
        super.NotifyPropertyRemoved(propertyDefinitions, stringPropertyDefinition, param2, originalPropertyInstances);
    }

    @Override
    public void NotifyPropertyRequiredChanged(PropertyDefinitions propertyDefinitions,
                                              StringPropertyDefinition stringPropertyDefinition) {
    }

    @Override
    public void NotifyNumericPropertyDefaultUnitSubTypeChanged(NumericDataPropertyDefinition
                                                                       numericDataPropertyDefinition,
                                                               PropertyDefinitions propertyDefinitions, int param2
            , int param3) {
    }

}
