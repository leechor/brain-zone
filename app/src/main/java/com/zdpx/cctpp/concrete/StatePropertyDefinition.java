package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.NumericDataType;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.simioEnums.NumericFormat;
import com.zdpx.cctpp.simioEnums.StateReferenceType;

/**
 *
 */
public class StatePropertyDefinition extends NumericDataPropertyDefinition {
    private StateReferenceType stateReferenceType;
    private boolean isAssignable;
    private boolean dontUseAssociatedObjects;

    public StatePropertyDefinition(String name) {
        super(name, NumericDataType.Real);
        this.stateReferenceType = StateReferenceType.AnyState;
        this.defaultString = super.NullNullString();
        this.dataFormat = DataFormat.List;
        this.isAssignable = false;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new StatePropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.StateProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.StateProperty_ClassDescription;
    }

    public boolean IsAssignable() {
        return this.isAssignable;
    }

    public void IsAssignable(boolean value) {
        this.isAssignable = value;
        super.propertyChangedEventHandler("IsAssignable");
    }

    public boolean getDontUseAssociatedObjects() {
        return this.dontUseAssociatedObjects;
    }

    public void setDontUseAssociatedObjects(boolean value) {
        this.dontUseAssociatedObjects = value;
        super.propertyChangedEventHandler("DontUseAssociatedObjects");
    }

    public StateReferenceType ReferenceType() {
        return this.stateReferenceType;
    }


    public void ReferenceType(StateReferenceType value) {
        this.stateReferenceType = value;
    }

    @Override
    public boolean QualifyAsValidReference(StringPropertyDefinition stringPropertyDefinition) {
        if (super.QualifyAsValidReference(stringPropertyDefinition)) {
            if (this.stateReferenceType == StateReferenceType.AnyState) {
                return true;
            }
            StatePropertyDefinition statePropertyDefinition = (StatePropertyDefinition) stringPropertyDefinition;
            if (this.ReferenceType() == StateReferenceType.QueueState && statePropertyDefinition.ReferenceType() == StateReferenceType.QueueStateModifyable) {
                return true;
            }
            if (this.ReferenceType() == StateReferenceType.QueueState && statePropertyDefinition.ReferenceType() == StateReferenceType.QueueStateRemovable) {
                return true;
            }
            if (this.ReferenceType() == StateReferenceType.QueueStateModifyable && statePropertyDefinition.ReferenceType() == StateReferenceType.QueueStateRemovable) {
                return true;
            }
            if (this.ReferenceType() == StateReferenceType.QueueStateRemovable && statePropertyDefinition.ReferenceType() == StateReferenceType.QueueStateModifyable) {
                return true;
            }
            if (statePropertyDefinition.ReferenceType() == this.stateReferenceType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.getGridPropertyItemList(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.StateProperty_ReferenceTypeName, gridItemProperty,
                -6, this.ReferenceType(), StateReferenceType.AnyState, PropertyGridFeel.none,
                EngineResources.StateProperty_ReferenceTypeDescription,
                new PropertyOperator_0<>(StateReferenceType.class, this::ReferenceType,
                        this::ReferenceType)));
        gridItemProperties.add(new GridItemProperty(EngineResources.NumericDataFormatName, gridItemProperty, -7,
                super.NumericDataFormat(), NumericFormat.Real, PropertyGridFeel.none,
                EngineResources.NumericDataFormatDescription,
                new PropertyOperator_0<>(NumericFormat.class, super::NumericDataFormat,
                        super::NumericDataFormat)));
        return gridItemProperties;
    }

    @Override
    public IValueProvider ValueProvider() {
        return new StateReferenceTypeValueProvider(super.NullNullString(), this.IsAssignable(),
                this.getDontUseAssociatedObjects(), this.stateReferenceType);

    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readEnumAttribute(xmlReader, "ReferenceType", this::ReferenceType, StateReferenceType.class);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "IsAssignable", t -> this.isAssignable = t);
    }

    @Override
    public Class<?> NativeObjectType() {
        return String.class;
    }

    @Override
    public void CopyFrom(StringPropertyDefinition stringPropertyDefinition) {
        if (stringPropertyDefinition instanceof StatePropertyDefinition) {
            StatePropertyDefinition statePropertyDefinition = (StatePropertyDefinition) stringPropertyDefinition;

            this.ReferenceType(statePropertyDefinition.ReferenceType());
        }
        super.CopyFrom(stringPropertyDefinition);
    }

}
