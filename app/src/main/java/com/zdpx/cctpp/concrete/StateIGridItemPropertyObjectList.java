package com.zdpx.cctpp.concrete;

import java.util.ArrayList;

public class StateIGridItemPropertyObjectList extends ArrayList<BaseStateIGridItemPropertyObject> {
    private AbsIntelligentPropertyObject absIntelligentPropertyObject;

    public StateIGridItemPropertyObjectList(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.absIntelligentPropertyObject = absIntelligentPropertyObject;
        AbsDefinition definition = (AbsDefinition) absIntelligentPropertyObject.getObjectDefinition();
        StateDefinitions stateDefinitions = definition.getStateDefinitions();
        for (BaseStatePropertyObject baseStatePropertyObject : stateDefinitions.StateProperties.values) {
            this.add(baseStatePropertyObject.CreateInstance(this));
        }
    }

    public void setAbsIntelligentObjectProperty(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.absIntelligentPropertyObject = absIntelligentPropertyObject;
    }

    public AbsIntelligentPropertyObject getAbsIntelligentObjectProperty() {
        return absIntelligentPropertyObject;
    }
}
