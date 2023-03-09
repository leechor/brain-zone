package org.licho.brain.concrete;

import lombok.Builder;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.NumericDataType;


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
    public String getObjectClassName() {
        return EngineResources.BooleanProperty_ClassName;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new BooleanPropertyRow(this, properties);
    }

    @Override
    public String getObjectDescription() {
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
