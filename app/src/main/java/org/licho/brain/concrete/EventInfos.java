package org.licho.brain.concrete;

import org.licho.brain.api.IEventDefinition;

import java.util.ArrayList;

/**
 *
 */
public class EventInfos extends ArrayList<EventInfo> {
    private AbsIntelligentPropertyObject absIntelligentPropertyObject;

    public EventInfos(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.absIntelligentPropertyObject = absIntelligentPropertyObject;
        AbsDefinition absDefinition = (AbsDefinition) absIntelligentPropertyObject.assignerDefinition;
        if (absDefinition != null) {
            for (IEventDefinition obj : absDefinition.getEventDefinitions()) {
                EventDefinition eventDefinition = (EventDefinition) obj;
                this.add(new EventInfo(eventDefinition, this));
            }
        }
    }

    public AbsIntelligentPropertyObject getAbsIntelligentObjectProperty() {
        return absIntelligentPropertyObject;
    }

    public void setAbsIntelligentObjectProperty(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.absIntelligentPropertyObject = absIntelligentPropertyObject;
    }
}
