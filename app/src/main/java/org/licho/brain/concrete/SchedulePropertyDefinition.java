package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.Enum13;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.enu.ScheduleType;

import java.util.List;

/**
 *
 */
public class SchedulePropertyDefinition extends StringPropertyDefinition {
    private ScheduleType scheduleType;

    public SchedulePropertyDefinition(String name) {
        this(name, ScheduleType.CapacitySchedule);
    }

    public SchedulePropertyDefinition(String name, ScheduleType scheduleType) {
        super(name);
        this.dataFormat = DataFormat.List;
        this.defaultString = super.NullNullString();
        this.scheduleType = scheduleType;
    }

    public ScheduleType TypeOfSchedule() {
        return this.scheduleType;
    }

    public void TypeOfSchedule(ScheduleType value) {
        this.scheduleType = value;
        this.owner.UpdateForNewPropertyDefinition(this);
        super.propertyChangedEventHandler("TypeOfSchedule");
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.ScheduleProperty_ClassName;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new SchedulePropertyRow(this, properties);
    }

    @Override
    public IValueProvider ValueProvider() {
        return new SchedulePropertyValueProvider(this);
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.ScheduleProperty_ClassDescription;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.method_2(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemPropertyByName =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.ScheduleProperty_ScheduleTypeName,
                gridItemPropertyByName, -20, this.TypeOfSchedule(), null, PropertyGridFeel.none,
                EngineResources.ScheduleProperty_ScheduleTypeDescription,
                new PropertyOperator_0<>(ScheduleType.class, this::TypeOfSchedule, this::TypeOfSchedule)));
        return gridItemProperties;
    }


    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readEnumAttribute(xmlReader, "ScheduleType", this::TypeOfSchedule, ScheduleType.class);
    }

    @Override
    public void GetAutoCompleteChoices(List<CompleteChoice> results, Enum13 enum13,
                                       IntelligentObjectDefinition intelligentObjectDefinition, boolean param3) {
        CompleteChoice completeChoice = new CompleteChoice(ScheduleAutoComplete.Instance().Name(), 26,
                ScheduleAutoComplete.Instance().Description());
        completeChoice.method_6(true);
        results.add(completeChoice);
        super.GetAutoCompleteChoices(results, enum13, intelligentObjectDefinition, param3);
    }

    @Override
    public IAutoComplete GetAutoCompleteObject(String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        if (ScheduleAutoComplete.Instance().Name().equalsIgnoreCase(name)) {
            return ScheduleAutoComplete.Instance();
        }
        return null;
    }

}
