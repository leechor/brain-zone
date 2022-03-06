package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.enu.ProductComplexityLevel;
import com.zdpx.cctpp.enu.PropertyGridFeel;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PhysicalCharacteristicsGridItemPropertyObject extends BaseStateIGridItemPropertyObject {

    private double[] values;

    public PhysicalCharacteristicsGridItemPropertyObject(AbsStatePropertyObject statePropertyObject,
                                                         StateIGridItemPropertyObjectList propertiesList) {
        super(statePropertyObject, propertiesList);
        this.values = new double[statePropertyObject.Parameters().length];
        for (int i = 0; i < statePropertyObject.initValues.size(); i++) {
            this.values[i] = statePropertyObject.initValues.get(i);
        }
    }

    public double getPropertyValuesByIndex(int index) {
        return this.values[index];
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    protected void setPropertyValuesByIndex(int index, double value) {
        double num = this.values[index];
        this.values[index] = value;
        if (value != num) {
            super.propertyChanged();
        }
    }

    protected String vmethod_2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.StatePropertyObject.Parameters().length; i++) {
            list.add(MessageFormat.format("{0} = {1:0.###}", this.StatePropertyObject.Parameters()[i].name,
                    this.values[i]));
        }
        return String.join(", ", list.toArray(new String[0]));
    }

    protected boolean vmethod_3(int param0) {
        AbsStatePropertyObject absStatePropertyObject = (AbsStatePropertyObject) this.StatePropertyObject;
        return !absStatePropertyObject.ParameterIsReadOnly(param0);
    }

    public GridItemProperties method_15(GridItemProperties gridItemProperties) {
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_PhysicalCharacteristics);
        String name = this.StatePropertyObject.Name();

        GridItemProperty itemProperty = new GridItemProperty(name, gridItemProperty, 39999, this.vmethod_2(),
                this.vmethod_2(), PropertyGridFeel.none, this.StatePropertyObject.Description(),
                new PropertyOperator_0<>(String.class, this::vmethod_2, (t) -> {
                }));
        itemProperty.ReadOnly(true);
        itemProperty.ComplexityLevel(ProductComplexityLevel.Advanced);
        gridItemProperties.add(itemProperty);
        AbsStatePropertyObject statePropertyObject = (AbsStatePropertyObject) this.StatePropertyObject;
        for (int i = 0; i < statePropertyObject.Parameters().length; i++) {
            if (this.vmethod_3(i)) {
                double num = this.values[i];
                GridItemProperty property = new GridItemProperty(statePropertyObject.Parameters()[i].name, itemProperty
                        , 40000 + i, num, num,
                        PropertyGridFeel.none, null,
                        new PhysicalCharacteristicsGridItemPropertyObject.DoublePropertyOperator_0(this, i));
                property.ComplexityLevel(ProductComplexityLevel.Advanced);
                gridItemProperties.add(property);
                property.ReadOnly(statePropertyObject.ParameterIsReadOnly(i));
            }
        }
        return gridItemProperties;
    }

    private class DoublePropertyOperator_0 extends PropertyOperator_0<Double> {
        public DoublePropertyOperator_0(PhysicalCharacteristicsGridItemPropertyObject param0, int param1) {
            super(Double.class, null, null);
            this.physicalCharacteristicsGridItemProperties = param0;
            this.index = param1;
        }

        @Override
        protected Double GetPropertyValue() {
            return this.physicalCharacteristicsGridItemProperties.getPropertyValuesByIndex(this.index);
        }

        @Override
        protected void SetPropertyValue(Double elementReferenceType) {
            this.physicalCharacteristicsGridItemProperties.setPropertyValuesByIndex(this.index, elementReferenceType);
        }

        private PhysicalCharacteristicsGridItemPropertyObject physicalCharacteristicsGridItemProperties;

        private int index;
    }

}
