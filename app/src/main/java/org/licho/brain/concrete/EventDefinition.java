package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.resource.Image;
import org.licho.brain.api.IEventDefinition;

import java.util.Objects;

/**
 *
 */
public class EventDefinition implements INotifyPropertyChanged, IGridObject, IOwner, ISearch, IItemDescriptor,
        IEventDefinition {
    public EventDefinitions EventDefinitions;
    public int eventIndex;
    private String name;

    private boolean isPrivate;
    private String description;

    public static EventDefinition readXmlEventDefinition(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                         AbstractGridObjectDefinition abstractGridObjectDefinition) {
        EventDefinition eventDefinition = null;
        if (Objects.equals(xmlReader.Name(), "Event")) {
            eventDefinition = new EventDefinition("__TEMP_EVENT___", false);
            abstractGridObjectDefinition.getEventDefinitions().InsertEventDefinition(eventDefinition);
            eventDefinition.readXml(xmlReader, intelligentObjectXml);
        }
        return eventDefinition;
    }

    public void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Event", (XmlReader attr) ->
        {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && !name.equals(this.Name())) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.getAbsDefinition() != null) {
                    this.Name(this.getAbsDefinition().GetUniqueName(name, false));
                } else {
                    this.Name(name);
                }
            }
            String description = attr.GetAttribute("Description");
            if (!Strings.isNullOrEmpty(description)) {
                this.Description(description);
            }
            SomeXmlOperator.readXmlBooleanAttribute(attr, "Public", this::IsPrivate);
        }, null);
    }


    public boolean IsPrivate() {
        return isPrivate;
    }

    public void IsPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
        this.propertyChangedEventHandler("IsPrivate");
    }


    private EventDefinitions eventDefinitions;

    public EventDefinition(String name, boolean isPrivate) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.description = "";
    }

    private void propertyChangedEventHandler(String name) {
        // TODO: 2021/11/11
    }

    private AbstractGridObjectDefinition getAbsDefinition() {
        if (this.eventDefinitions != null) {
            return this.eventDefinitions.getParent();
        }
        return null;
    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }

    @Override
    public Object Item() {
        return null;
    }

    @Override
    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        String name = this.name;
        this.name = value;
        this.propertyChangedEventHandler("Name");
        if (this.getAbsDefinition() != null) {
            this.getAbsDefinition().NotifyEventDefinitionRenamed(this, name);
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String Description() {
        return this.description;
    }

    @Override
    public void Description(String description) {
        this.description = description;
        this.propertyChangedEventHandler("Description");
    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return this.name;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    public void setName(String name) {
        String _name = this.name;
        this.name = name;
        this.propertyChangedEventHandler("Name");
        if (this.getAbsDefinition() != null) {
            this.getAbsDefinition().NotifyEventDefinitionRenamed(this, _name);
        }
    }

    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public EventInfo createEventInfo(EventInfos eventInfos) {
        return new EventInfo(this, eventInfos);
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        IntelligentObjectDefinition intelligentObjectDefinition = (IntelligentObjectDefinition) parent;
        return intelligentObjectDefinition != null && this.EventDefinitions == intelligentObjectDefinition.getEventDefinitions();
    }
}
