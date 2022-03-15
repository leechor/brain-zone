package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.enu.Enum13;

import java.util.List;

/**
 *
 */
public class ScheduleAutoComplete implements IAutoComplete {
    private static final ScheduleAutoComplete instance = new ScheduleAutoComplete();

    private ScheduleAutoComplete() {
    }

    public static ScheduleAutoComplete Instance() {
        return ScheduleAutoComplete.instance;
    }

    public String Name() {
        return "Schedule";
    }

    public String Description() {
        return EngineResources.DescriptionFor_PropertyFunction_ScheduleReference;
    }

    public void GetAutoCompleteChoices(List<CompleteChoice> results, Enum13 enum13,
                                       IntelligentObjectDefinition intelligentObjectDefinition, boolean param3) {
        CompleteChoice value = new CompleteChoice("Value( dateTime )", 2,
				EngineResources.DescriptionFor_ScheduleFunction_Value);
        value.method_4(true);
        results.add(value);
        CompleteChoice nextValue = new CompleteChoice("NextValue( dateTime )", 2,
				EngineResources.DescriptionFor_ScheduleFunction_NextValue);
        nextValue.method_4(true);
        results.add(nextValue);
        CompleteChoice timeOfNextValue = new CompleteChoice("TimeOfNextValue( dateTime )", 2,
				EngineResources.DescriptionFor_ScheduleFunction_TimeOfNextValue);
        timeOfNextValue.method_4(true);
        results.add(timeOfNextValue);
        CompleteChoice timeUntilNextValue = new CompleteChoice("TimeUntilNextValue( dateTime )", 2,
				EngineResources.DescriptionFor_ScheduleFunction_TimeUntilNextValue);
        timeUntilNextValue.method_4(true);
        results.add(timeUntilNextValue);
    }


    public IAutoComplete GetAutoCompleteObject(String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        return null;
    }
}
