package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.element.EventPropertyRow;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.PropertyGridFeel;

@PropertyDefinitionName("EventProperty")
public class EventPropertyDefinition extends StringPropertyDefinition {
    private boolean bool_9;

    public EventPropertyDefinition(String name) {
        super(name);
        this.defaultString = super.NullNullString();
        this.dataFormat = DataFormat.List;
    }

    public void method_39(boolean value) {
        this.bool_9 = value;
    }


    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new EventPropertyRow(this, properties);
    }

    @Override
    public GridItemProperty GetGridItemProperties(PropertyDefinitions propertyDefinitions) {
        return new GridItemProperty(this.Name(), EngineResources.CategoryName_General,
                this.overRidePropertyIndex + 1000, super.DefaultString(), null, PropertyGridFeel.none,
                super.GetDisplayName(propertyDefinitions), super.Description(),
                new PropertyOperator_0<>(String.class, super::DefaultString, super::DefaultString));
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.EventProperty_ClassName;
    }


    @Override
    public String GetGridObjectDescription() {
        return EngineResources.EventProperty_ClassDescription;
    }

}
