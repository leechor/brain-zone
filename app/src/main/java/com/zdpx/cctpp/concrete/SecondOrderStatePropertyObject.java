package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.enu.UnitType;

/**
 *
 */
public class SecondOrderStatePropertyObject extends CostStatePropertyObject {
    public SecondOrderStatePropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
        super.addParameter("Acceleration", 0.0, EngineResources.DescriptionFor_StateParameter_Acceleration);
        super.addParameter("AccelerationDuration", 0.0,
                EngineResources.DescriptionFor_StateParameter_AccelerationDuration);
    }

    public double AccelerationValue() {
        return this.initValues.get(1);
    }

    public void AccelerationValue(double value) {
        this.initValues.set(1, value);
        super.propertyChanged("AccelerationValue");
    }

    public double DurationValue() {
        return this.initValues.get(2);
    }

    public void DurationValue(double value) {
        this.initValues.set(2, value);
        super.propertyChanged("DurationValue");
    }

    @Override
    public int IconIndex() {
        return 2;
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new DistanceStateGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.SecondOrderState_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.SecondOrderState_ClassDescription;
    }

    @Override
    protected int LastPropertyNumber() {
        return super.LastPropertyNumber() - 2;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.GetGridPropertyItemList(gridItemProperties, gridObjectDefinition);

        gridItemProperties.removeIf(t ->
                StringHelper.equalsLocal(EngineResources.State_DisplayFormat_Name, t.DisplayName()));
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);

        gridItemProperties.add(new GridItemProperty(EngineResources.SecondOrderState_PropertyName_InitialAccelerationValue,
                gridItemProperty, super.LastPropertyNumber() - 1, this.AccelerationValue(), 0.0,
                PropertyGridFeel.none, EngineResources.SecondOrderState_PropertyDescription_InitialAccelerationValue,
                new PropertyOperator_0<>(Double.class, this::AccelerationValue, this::AccelerationValue)));

        gridItemProperties.add(new GridItemProperty(EngineResources.SecondOrderState_PropertyName_InitialAccelerationDurationValue,
                gridItemProperty, super.LastPropertyNumber() - 2, this.DurationValue(), 0.0,
                PropertyGridFeel.none, EngineResources.SecondOrderState_PropertyDescription_InitialAccelerationDurationValue,
                new PropertyOperator_0<>(Double.class, this::DurationValue, this::DurationValue)));

        gridItemProperties.add(new GridItemProperty(EngineResources.State_DisplayFormat_Name, gridItemProperty, -9,
                super.DisplayFormat(), "", PropertyGridFeel.edit, EngineResources.State_DisplayFormat_Description,
                new PropertyOperator_0<>(String.class, super::DisplayFormat, super::DisplayFormat)));
        return gridItemProperties;
    }

    @Override
    public UnitType GetUnitTypeForParameter(int param0) {
        if (param0 == 1) {
            return AbouleUnitTypeConvertor.smethod_2(super.UnitType());
        }
        if (param0 == 2) {
            return UnitType.Time;
        }
        return super.GetUnitTypeForParameter(param0);
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader) {
        super.ReadXmlAttributes(xmlReader);
        SomeXmlOperator.readXmlDoubleAttribute(xmlReader, "InitialAcceleration", this::AccelerationValue);
        SomeXmlOperator.readXmlDoubleAttribute(xmlReader, "InitialDuration", this::DurationValue);
    }
}

