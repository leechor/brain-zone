package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.enu.UnitType;

/**
 *
 */
public class SizeStatePropertyObject extends AbsStatePropertyObject {

    public SizeStatePropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
        super.addParameter("Length", 1.0, EngineResources.DescriptionFor_StateParameter_Length);
        super.addParameter("Width", 1.0, EngineResources.DescriptionFor_StateParameter_Width);
        super.addParameter("Height", 1.0, EngineResources.DescriptionFor_StateParameter_Height);
        super.addParameter("Length.Rate", 0.0, "The rate of change of the length of this object");
        super.addParameter("Width.Rate", 0.0, "The rate of change of the width of this object");
        super.addParameter("Height.Rate", 0.0, "The rate of change of the height of this object");
    }

    public double Length() {
        return this.initValues.get(0);
    }

    public void Length(double value) {
        this.initValues.add(0, value);
    }

    public double Width() {
        return this.initValues.get(1);
    }

    public void Width(double value) {
        this.initValues.add(1, value);
    }

    public double Height() {
        return this.initValues.get(2);
    }

    public void Height(double value) {
        this.initValues.add(2, value);
    }

    @Override
    protected boolean ParameterIsReadOnly(int param0) {
        return (param0 == 0 && this.StateDefinitions != null &&
                this.StateDefinitions.AbsDefinition != null &&
                this.StateDefinitions.AbsDefinition instanceof LinkDefinition) ||
                param0 == 3 || param0 == 4 || param0 == 5;
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new SizeStateGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.SizeState_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.SizeState_ClassDescription;
    }

    @Override
    protected int LastPropertyNumber() {
        return super.LastPropertyNumber() - 3;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.GetGridPropertyItemList(gridItemProperties, gridObjectDefinition);
        GridItemProperty param2 = gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.SizeState_Length_Name, param2,
                super.LastPropertyNumber() - 1, this.Length(), 1.0, PropertyGridFeel.none,
                EngineResources.SizeState_Length_Description,
                new PropertyOperator_0<>(Double.class, this::Length, this::Length)));
        gridItemProperties.add(new GridItemProperty(EngineResources.SizeState_Width_Name, param2,
                super.LastPropertyNumber() - 2, this.Width(), 1.0, PropertyGridFeel.none,
                EngineResources.SizeState_Width_Description,
                new PropertyOperator_0<>(Double.class, this::Width, this::Width)));
        gridItemProperties.add(new GridItemProperty(EngineResources.SizeState_Height_Name, param2,
                super.LastPropertyNumber() - 3, this.Height(), 1.0, PropertyGridFeel.none,
                EngineResources.SizeState_Height_Description,
                new PropertyOperator_0<>(Double.class, this::Height, this::Height)));
        return gridItemProperties;
    }

    @Override
    public UnitType GetUnitTypeForParameter(int unitType) {
        if (unitType == 0 || unitType == 1 || unitType == 2) {
            return UnitType.Length;
        }
        return super.GetUnitTypeForParameter(unitType);
    }

    @Override
    public int IconIndex() {
        return 4;
    }
}
