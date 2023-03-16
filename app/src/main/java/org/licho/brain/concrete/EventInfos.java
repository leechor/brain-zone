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
        AbstractGridObjectDefinition abstractGridObjectDefinition = (AbstractGridObjectDefinition) absIntelligentPropertyObject.assignerDefinition;
        if (abstractGridObjectDefinition != null) {
            for (IEventDefinition obj : abstractGridObjectDefinition.getEventDefinitions()) {
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
