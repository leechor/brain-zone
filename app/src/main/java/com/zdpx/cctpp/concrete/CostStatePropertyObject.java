package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.enu.UnitType;

/**
 *
 */
public class CostStatePropertyObject extends AbsStatePropertyObject {
    public CostStatePropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
        super.addParameter("Rate", 0.0, EngineResources.DescriptionFor_StateParameter_Rate);
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new CostStateGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }

    public double RateValue() {
        return this.initValues.get(0);
    }

    public void RateValue(double value) {
        this.initValues.set(0, value);
        super.propertyChanged("RateValue");
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.FirstOrderState_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.FirstOrderState_ClassDescription;
    }

    @Override
    protected int LastPropertyNumber() {
        return super.LastPropertyNumber() - 1;
    }

    @Override
    public int IconIndex() {
        return 1;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.GetGridPropertyItemList(gridItemProperties, gridObjectDefinition);

        gridItemProperties.removeIf(t ->
                StringHelper.equalsLocal(EngineResources.State_DisplayFormat_Name, t.DisplayName()));
        GridItemProperty gridItemPropertyByName =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.FirstOrderState_PropertyName_InitialRateValue,
                gridItemPropertyByName, super.LastPropertyNumber() - 1, this.RateValue(), 0.0, PropertyGridFeel.edit,
                EngineResources.FirstOrderState_PropertyDescription_InitialRateValue,
                new PropertyOperator_0<>(Double.class, this::RateValue, this::RateValue)));
        gridItemProperties.add(new GridItemProperty(EngineResources.State_DisplayFormat_Name, gridItemPropertyByName,
                -9, super.DisplayFormat(), "", PropertyGridFeel.edit, EngineResources.State_DisplayFormat_Description,
                new PropertyOperator_0<>(String.class, super::DisplayFormat, super::DisplayFormat)));
        return gridItemProperties;
    }

    @Override
    public UnitType GetUnitTypeForParameter(int param0) {
        if (param0 == 0) {
            return AbouleUnitTypeConvertor.smethod_1(super.UnitType());
        }
        return super.GetUnitTypeForParameter(param0);
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader) {
        super.ReadXmlAttributes(xmlReader);
        SomeXmlOperator.readXmlDoubleAttribute(xmlReader, "InitialRate", this::RateValue);
    }
}



