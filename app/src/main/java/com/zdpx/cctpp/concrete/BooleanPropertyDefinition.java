package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.NumericDataType;

/**
 *
 */
public class BooleanPropertyDefinition extends NumericDataPropertyDefinition {

    public BooleanPropertyDefinition(String name) {
        super(name, NumericDataType.None);
        this.defaultString = "False";
        this.dataFormat = DataFormat.Bool;
    }

    public boolean DefaultBooleanValue() {
        return !this.defaultString.equals("False");
    }

    public void DefaultBooleanValue(boolean value) {
        if (value) {
            this.defaultString = "True";
        } else {
            this.defaultString = "False";
        }
        super.propertyChangedEventHandler("DefaultBooleanValue");
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.BooleanProperty_ClassName;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new BooleanPropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.BooleanProperty_ClassDescription;
    }

    @Override
    public GridItemProperty GetGridItemProperties(PropertyDefinitions param0) {
//        return new GridItemProperty(this.Name, super.CategoryName, this.propertyIndex + 1000,
//				super.DefaultDefaultString, "False", PropertyGridFeel.list, super.GetDisplayName(param0),
//				super.Description, new PropertyOperator_0<String>(new Delegate31<String>(this.method_58),
//				new Action<String>(this.method_59), new Delegate36(this.method_60)));
        return null;
    }

    @Override
    protected IValueProvider ValueProvider() {
        return ValueProvider.Instance(IntelligentObjectDefinitionValueProvider.class);
    }

    @Override
    public Class<?> NativeObjectType() {
        return Boolean.class;
    }
}
