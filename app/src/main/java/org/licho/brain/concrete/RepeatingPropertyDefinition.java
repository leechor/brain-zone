package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

@PropertyDefinitionName("RepeatingProperty")
public class RepeatingPropertyDefinition extends StringPropertyDefinition {
    public PropertyDefinitions propertyDefinitions;

    public RepeatingPropertyDefinition(String name, GridObjectDefinition gridObjectDefinition) {
        super(name);
        this.propertyDefinitions = new PropertyDefinitions(gridObjectDefinition, this);
    }


    public PropertyDefinitions getPropertyDefinitions() {
        return propertyDefinitions;
    }

    public void setPropertyDefinitions(PropertyDefinitions propertyDefinitions) {
        this.propertyDefinitions = propertyDefinitions;
    }

    public void setTargetObjectDefinition(GridObjectDefinition gridObjectDefinition) {
        this.propertyDefinitions.setTargetObjectDefinition(gridObjectDefinition);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new RepeatStringPropertyRow(this, properties);
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.RepeatingProperty_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.RepeatingProperty_ClassDescription;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        return super.getGridPropertyItemList(gridItemProperties, gridObjectDefinition);
    }

    @Override
    public int IconIndex() {
        return 7;
    }

    @Override
    public String Name() {
        return super.Name();
    }

    @Override
    public void Name(String value) {
        super.Name(value);
        for (StringPropertyDefinition stringPropertyDefinition : this.propertyDefinitions.values) {
            stringPropertyDefinition.triggerChangeName(value);
        }
    }

    @Override
    protected boolean ReadXmlBody(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                  IIdentityName identityName) {
        return super.ReadXmlBody(xmlReader, intelligentObjectXml, identityName) ||
                this.propertyDefinitions.readXml(xmlReader, intelligentObjectXml, identityName);
    }

    public PropertiesRunSpaceWrapper getPropertiesRunSpaceWrapper(IRunSpace runSpace,
                                                                  IntelligentObjectRunSpace intelligentObjectRunSpace) {
        RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow)
                intelligentObjectRunSpace.myElementInstance.properties.get(this.overRidePropertyIndex);
        return repeatStringPropertyRow.getPropertiesRunSpaceWrapper(runSpace, intelligentObjectRunSpace);
    }

    @Override
    public IAutoComplete GetAutoCompleteObject(String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.propertyDefinitions.findStringPropertyDefinitionInfoByName(name);
    }

    @Override
    public Class<?> NativeObjectType() {
        return PropertyDescriptors.class;
    }
}



