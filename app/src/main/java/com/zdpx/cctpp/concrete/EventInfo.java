package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class EventInfo {

    private EventDefinition eventDefinition;
    private EventInfos eventInfos;

    public EventInfo(EventDefinition eventDefinition, EventInfos eventInfos) {
        this.eventDefinition = eventDefinition;
        this.eventInfos = eventInfos;
    }

    public Event getEventByDataSource(AbsBaseRunSpace dataSource) {
        return dataSource.getEvents()[this.eventDefinition.getEventIndex()];
    }

    public void setEventDefinition(EventDefinition eventDefinition) {
        this.eventDefinition = eventDefinition;
    }
}
