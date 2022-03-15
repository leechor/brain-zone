package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.enu.UnitType;

/**
 *
 */
public class BaseStateIGridItemPropertyObject implements IAutoComplete, IGridItemPropertyObject {

    protected boolean bool_0;
    public BaseStatePropertyObject StatePropertyObject;

    private StateIGridItemPropertyObjectList StateIGridItemPropertyObjectList;


    private Double initialValue;
    private NumericDataPropertyDefinition NumericDataProperty;

    public Double getInitialValue() {
        return this.initialValue == null ? this.StatePropertyObject.Value() : this.initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }


    public BaseStateIGridItemPropertyObject(BaseStatePropertyObject stateProperty,
                                            StateIGridItemPropertyObjectList propertiesList) {
        this.StateIGridItemPropertyObjectList = propertiesList;
        this.StatePropertyObject = stateProperty;
        this.NumericDataProperty = null;
        this.bool_0 = false;
        this.initialValue = null;
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        int num = this.StatePropertyObject.index + 1000000;
        String name = this.StatePropertyObject.name;
        PropertyGridFeel propertyGridFeel = PropertyGridFeel.none;
        String displayName = this.StatePropertyObject.DisplayName();
        String description = this.StatePropertyObject.Description();
        switch (this.StatePropertyObject.numericDataType) {
            case Real: {
                return new GridItemProperty(name, EngineResources.CategoryName_General, num, this.InitialValue(),
                        this.StatePropertyObject.value, propertyGridFeel, displayName, description,
                        new PropertyOperator_0<>(Double.class, this::InitialValue, this::InitialValue));
            }
            case Integer: {
                return new GridItemProperty(name, EngineResources.CategoryName_General, num, this.InitialValue(),
                        this.StatePropertyObject.value, propertyGridFeel, displayName, description,
                        new PropertyOperator_0<>(Integer.class,
                                () -> (int) this.InitialValue(), this::InitialValue));
            }
            case Boolean: {
                return new GridItemProperty(name, EngineResources.CategoryName_General, num,
                        this.InitialValue() != 0.0, this.StatePropertyObject.value, propertyGridFeel,
                        displayName, description, new PropertyOperator_0<>(Boolean.class,
                        () -> this.InitialValue() != 0.0,
                        (Boolean t) -> this.InitialValue(Boolean.TRUE.equals(t) ? 1.0 : 0.0)));
            }
            default:
                return new GridItemProperty();
        }
    }

    @Override
    public void AlternateGetGridItemProperties(PropertyDefinitions definitions, GridItemProperties gridItemProperties) {

    }

    @Override
    public void UpdateGridPropertyValue(int param0, Object param1) {

    }

    @Override
    public String[] GetListValues() {
        return null;
    }

    public BaseStatePropertyObject getStatePropertyObject() {
        return StatePropertyObject;
    }

    public void setStatePropertyObject(BaseStatePropertyObject statePropertyObject) {
        this.StatePropertyObject = statePropertyObject;
    }

    public StateIGridItemPropertyObjectList getGridItemPropertiesList() {
        return StateIGridItemPropertyObjectList;
    }

    public void setGridItemPropertiesList(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        this.StateIGridItemPropertyObjectList = stateIGridItemPropertyObjectList;
    }


    protected void propertyChanged() {
        if (this.StateIGridItemPropertyObjectList != null
                && this.StateIGridItemPropertyObjectList.getAbsIntelligentObjectProperty() != null
                && this.StateIGridItemPropertyObjectList.getAbsIntelligentObjectProperty() instanceof IntelligentObject) {
            ((IntelligentObject) this.getGridItemPropertiesList().getAbsIntelligentObjectProperty()).propertyChanged(this);
        }
    }

    public UnitType UnitType() {
        return this.StatePropertyObject.getPropertiesUnitType(this.StateIGridItemPropertyObjectList.getAbsIntelligentObjectProperty().getProperties());
    }

    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace param0) {
        return new BaseQueueGridItemTrace(this, param0);
    }

    public double getValue(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (this.NumericDataProperty != null) {
            NumericDataPropertyRow numericDataPropertyRow =
                    (NumericDataPropertyRow) intelligentObjectRunSpace.myElementInstance.properties.values.get(this.NumericDataProperty.overRidePropertyIndex);
            return IntelligentObjectProperty.ExpressionResult.toDouble(
                    numericDataPropertyRow.getExpressionResult(null,
                            intelligentObjectRunSpace.ParentObjectRunSpace));
        }
        return this.InitialValue();
    }

    public double InitialValue() {
        if (this.initialValue != null) {
            return this.initialValue;
        }
        return this.StatePropertyObject.getUnitValue();
    }

    public void InitialValue(double value) {
        this.initialValue = value;
        this.NumericDataProperty = null;
        this.propertyChanged();
    }

    protected boolean haveState() {
        return this.StatePropertyObject.getStateDimensions() > 0 || this.StatePropertyObject.getBoundTableName() != null;
    }
}
