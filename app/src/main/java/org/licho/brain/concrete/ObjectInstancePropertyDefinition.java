package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.simioEnums.ValidObjectType;

@PropertyDefinitionName("ObjectInstanceProperty")
public class ObjectInstancePropertyDefinition extends StringPropertyDefinition {
    private ValidObjectType validObjectType;
    private boolean filterToResources;

    public ObjectInstancePropertyDefinition(String name) {
        super(name);
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
    }

    public ObjectInstancePropertyDefinition(String name, ValidObjectType validObjectType) {
        super(name);
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
        this.validObjectType = validObjectType;
    }

    public ValidObjectType ValidObjectType() {
        return this.validObjectType;
    }

    public void ValidObjectType(ValidObjectType value) {
        this.validObjectType = value;
        super.propertyChangedEventHandler("ValidObjectType");
    }

    public boolean FilterToResources() {
        return this.filterToResources;
    }

    public void FilterToResources(boolean value) {
        this.filterToResources = value;
        super.propertyChangedEventHandler("FilterToResources");
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new ObjectInstancePropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.ObjectInstanceProperty_ClassName;
    }

    public boolean valid(ValidObjectType validObjectType) {
        switch (validObjectType) {
            case Object:
                return true;
            case FixedObject:
                return validObjectType == ValidObjectType.FixedObject;
            case Link:
                return validObjectType == ValidObjectType.Link;
            case Node:
                return validObjectType == ValidObjectType.Node;
            case Agent:
                return validObjectType == ValidObjectType.Agent || validObjectType == ValidObjectType.Entity || validObjectType == ValidObjectType.Transporter;
            case Entity:
                return validObjectType == ValidObjectType.Entity || validObjectType == ValidObjectType.Transporter;
            case Transporter:
                return validObjectType == ValidObjectType.Transporter;
            default:
                return false;
        }
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.GetGridPropertyItemList(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty("Filter to Resources", gridItemProperty, -4874,
                this.FilterToResources(), false, PropertyGridFeel.list, "Restricts the range of valid values to " +
                "objects that are resources.", new PropertyOperator_0<Boolean>(Boolean.class, this::FilterToResources,
                this::FilterToResources)));
        return gridItemProperties;
    }

    @Override
    IValueProvider ValueProvider() {
        return new ObjectInstancePropertyDefinitionValueProvider(this);
    }
}
