package org.licho.brain.concrete;

/**
 *
 */
public class SchedulePropertyValueProvider implements IValueProvider {
    	private SchedulePropertyDefinition scheduleProperty;

    public SchedulePropertyValueProvider(SchedulePropertyDefinition schedulePropertyDefinition) {
        		this.scheduleProperty = schedulePropertyDefinition;

    }

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        return new String[0];
    }
}
