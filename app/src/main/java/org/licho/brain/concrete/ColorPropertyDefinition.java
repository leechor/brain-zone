package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.utils.simu.system.Color;

@PropertyDefinitionName("ColorProperty")
public class ColorPropertyDefinition extends NumericDataPropertyDefinition {
    public ColorPropertyDefinition(String name) {
        super(name, NumericDataType.None);
        super.DefaultString("Black");
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.ColorProperty_ClassName;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new ColorPropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.ColorProperty_ClassDescription;
    }

    @Override
    public GridItemProperty GetGridItemProperties(PropertyDefinitions propertyDefinitions) {
        return new GridItemProperty(this.Name(), super.CategoryName(), this.overRidePropertyIndex + 1000, Color.Black,
                Color.Black, PropertyGridFeel.color, super.GetDisplayName(propertyDefinitions), super.Description(),
                new PropertyOperator_0<>(Color.class, () -> ColorOperator.smethod_0(super.DefaultString()),
                        (Color t) -> super.DefaultString(t.Name()),
                        () -> this.ValueProvider().getValue(null)));
    }

    @Override
    protected IValueProvider ValueProvider() {
        return FunctionProvider.InstanceStatic(ColorPropertyValueProvider.class);
    }

    @Override
    public Class<?> NativeObjectType() {
        return Color.class;
    }


}
