package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.brainApi.IEventDefinition;
import org.licho.brain.brainApi.IEventDefinitions;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class EventDefinitions implements IEventDefinitions {
    // AbsDefinition
    public AbsDefinition parent;
    private BindingList<EventDefinition> eventDefinitions;
    private ActionRun actionRun;
    public BindingList<EventDefinition> EventDefinitionBindingList;

    public EventDefinitions(AbsDefinition absDefinition) {
        this.parent = absDefinition;
        this.EventDefinitionBindingList = new BindingList<>();
    }

    @Override
    public IEventDefinition addEvent(String name) {
        return null;
    }

    public AbsDefinition getParent() {
        return parent;
    }

    public BindingList<EventDefinition> getEventDefinitions() {
        return eventDefinitions;
    }

    public void setEventDefinitions(BindingList<EventDefinition> eventDefinitions) {
        this.eventDefinitions = eventDefinitions;
    }

    public ActionRun getActionRun() {
        return actionRun;
    }

    public void setActionRun(ActionRun actionRun) {
        this.actionRun = actionRun;
    }

    @Override
    public IEventDefinition getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IEventDefinition getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IEventDefinition> iterator() {
        return new EventDefinitions.EventDefinitionOperator(this);
    }

    public void method_3(EventDefinition capacityChanged) {
    }

    public void InsertEventDefinition(EventDefinition eventDefinition) {
        eventDefinition.EventDefinitions = this;
        this.InsertEventDefinition(this.EventDefinitionBindingList.values.size(), eventDefinition);
    }

    private void InsertEventDefinition(int index, EventDefinition eventDefinition) {
        if (eventDefinition.EventDefinitions == null) {
            eventDefinition.EventDefinitions = this;
        }
        this.EventDefinitionBindingList.Insert(index, eventDefinition);
        List<AbsPropertyObject> list = this.parent.getAssociatedInstances();
        for (AbsPropertyObject absPropertyObject : list) {
            AbsIntelligentPropertyObject absIntelligentPropertyObject =
                    (AbsIntelligentPropertyObject) absPropertyObject;
            EventInfos eventInfos = absIntelligentPropertyObject.EventInfos;
            eventInfos.add(index, eventDefinition.createEventInfo(eventInfos));
        }
        int num = 0;
        for (Object obj : this) {
            EventDefinition definition = (EventDefinition) obj;
            definition.eventIndex = num++;
        }
        this.parent.NotifyEventAdded(this, eventDefinition, index);
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Events", null,
                (XmlReader body) -> EventDefinition.readXmlEventDefinition(body, intelligentObjectXml,
                        this.parent) != null);
    }

    public void addEventDefinition(EventDefinition eventDefinition) {
        this.EventDefinitionBindingList.Add(eventDefinition);
    }

    public class EventDefinitionOperator implements Iterator<IEventDefinition> {
        private EventDefinitions eventDefinitions;
        private int index;

        public EventDefinitionOperator(EventDefinitions iEventDefinitions) {
            this.eventDefinitions = iEventDefinitions;
            this.index = -1;
        }

        @Override
        public boolean hasNext() {
            var size = this.eventDefinitions.EventDefinitionBindingList.values.size();
            return size > 0 && this.index < size - 1;
        }

        @Override
        public IEventDefinition next() {
            this.index++;
            return this.eventDefinitions.EventDefinitionBindingList.values.get(this.index);
        }

        public void Reset() {
            this.index = -1;
        }

        public Object Current() {
            return this.eventDefinitions.EventDefinitionBindingList.values.get(this.index);
        }

    }
}
