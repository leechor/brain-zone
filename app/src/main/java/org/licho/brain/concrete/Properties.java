package org.licho.brain.concrete;

import cn.hutool.core.date.DateTime;
import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.api.IProperties;
import org.licho.brain.api.IProperty;
import org.licho.brain.api.IPropertyReader;
import org.licho.brain.api.IPropertyReaders;
import org.licho.brain.api.IRow;
import org.licho.brain.utils.simu.system.IDataErrorInfo;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 */
public final class Properties extends BindingList<IntelligentObjectProperty> implements INotifyPropertyChanged,
        IGridObject, IProperties, ISearch, IDataErrorInfo, Comparable<Properties>, IRow, IPropertyReaders {
    public AbsPropertyObject propertyObject;

    public PropertyDefinitions propertyDefinitions;

    private RepeatStringPropertyRow repeatStringPropertyRow;
    private EventHandler<PropertyChangedEventArgs> propertyChangedEventHandler;


    public Properties(PropertyDefinitions propertyDefinitions, AbsPropertyObject propertyObject,
                      RepeatStringPropertyRow repeatStringPropertyRow) {
        this.propertyDefinitions = propertyDefinitions;
        this.propertyObject = propertyObject;
        this.repeatStringPropertyRow = repeatStringPropertyRow;
        for (StringPropertyDefinition propertyDefinitionInfo : propertyDefinitions.values) {
            IntelligentObjectProperty property = propertyDefinitionInfo.CreateInstance(this);
            super.Add(property);
        }
    }

    public void addPropertyChangedEventHandler(IEvent<PropertyChangedEventArgs> value) {
        EventHandler.subscribe(this.propertyChangedEventHandler, value);
    }

    public void removePropertyChangedEventHandler(IEvent<PropertyChangedEventArgs> value) {
        EventHandler.unSubscribe(this.propertyChangedEventHandler, value);
    }

    public void copy(Properties properties) {
        this.copy(properties, -1);
    }

    public void copy(Properties properties, int count) {
        // TODO: 2021/11/11
    }

    IntelligentObjectProperty findByName(String name) {
        IntelligentObjectProperty intelligentObjectProperty =
                this.search((IntelligentObjectProperty t) -> Objects.equals(t.getStringPropertyDefinition().Name(), name));
        if (intelligentObjectProperty == null) {
            throw new IllegalArgumentException("No property of that name exists on this object.");
        }
        return intelligentObjectProperty;
    }

    public IntelligentObjectProperty search(Predicate<IntelligentObjectProperty> match) {
        if (match == null) {
            throw new IllegalArgumentException("match");
        }

        for (IntelligentObjectProperty objectProperty : this.values) {
            if (match.test(objectProperty)) {
                return objectProperty;
            }
        }
        return null;
    }

    public void writeXmlProperties(XmlWriter xmlWriter, CommonItems commonItems) {

    }

    public boolean readXmlProperties(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        xmlReader.MoveToContent();
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, "Properties", null, (XmlReader beforeRead) ->        {
            if (this.propertyObject != null) {
                this.propertyObject.LoadOldDefaultValuesForLoadFrom(intelligentObjectXml);
            }
            return null;
        }, (XmlReader body) -> IntelligentObjectProperty.readXml(body, intelligentObjectXml, this) != null, null);

    }

    @Override
    protected void InsertItem(int index, IntelligentObjectProperty intelligentObjectProperty) {
        if (intelligentObjectProperty != null) {
            intelligentObjectProperty.setProperties(this);
        }
        super.InsertItem(index, intelligentObjectProperty);
        intelligentObjectProperty.NotifyGroupInserted();
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
    public String getErrorByIndex(String propertyName) {
        return null;
    }

    @Override
    public String getError() {
        return null;
    }

    @Override
    public IProperty getByName(String name) {
        return null;
    }

    @Override
    public IPropertyReader GetProperty(String Name) {
        return null;
    }

    @Override
    public IProperties getProperties() {
        return null;
    }

    @Override
    public DateTime getTimeIndexStartTime() {
        return null;
    }

    @Override
    public DateTime getTimeIndexEndTime() {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IProperty getByIndex(int index) {
        return null;
    }

    public AbsPropertyObject getPropertyObject() {
        return propertyObject;
    }

    public void setPropertyObject(AbsPropertyObject propertyObject) {
        this.propertyObject = propertyObject;
    }

    public PropertyDefinitions getPropertyDefinitions() {
        return propertyDefinitions;
    }

    public void setPropertyDefinitions(PropertyDefinitions propertyDefinitions) {
        this.propertyDefinitions = propertyDefinitions;
    }

    @Override
    public int compareTo(Properties o) {
        return 0;
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


    public void PropertyUpdating(IntelligentObjectProperty objectProperty) {
        this.propertyObject.PropertyUpdating(objectProperty);
    }

    public void init() {
        for (IntelligentObjectProperty intelligentObjectProperty : this.values) {
            if (intelligentObjectProperty instanceof RepeatStringPropertyRow) {
                RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow) intelligentObjectProperty;
                for (Properties properties : repeatStringPropertyRow.PropertyDescriptors.values) {
                    properties.init();
                }
            }

            if (this.propertyObject != null) {
                this.propertyObject.PropertyUpdating(intelligentObjectProperty);
            }

            intelligentObjectProperty.processPropertyChange();
            if (this.propertyObject != null) {
                this.propertyObject.PropertyUpdated(intelligentObjectProperty,
                        intelligentObjectProperty.getError() != null, null);
            }
        }
    }

    public void propertyInstanceAction(Action<IntelligentObjectProperty> propertyInstanceAction) {
        for (var intelligentObjectProperty : this.AllPropertyInstances()) {
            propertyInstanceAction.apply(intelligentObjectProperty);
        }
    }

    public List<IntelligentObjectProperty> AllPropertyInstances() {
        List<IntelligentObjectProperty> result = new ArrayList<>();
        // from 14
        for (IntelligentObjectProperty intelligentObjectProperty : this.values) {
            if (intelligentObjectProperty instanceof RepeatStringPropertyRow) {
                RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow) intelligentObjectProperty;
                result.add(repeatStringPropertyRow);

                for (Properties properties : repeatStringPropertyRow.Tuples().values) {
                    result.addAll(properties.AllPropertyInstances());
                }
            } else {
                result.add(intelligentObjectProperty);
            }
        }
        return result;
    }

    public Object PropertyUpdated(IntelligentObjectProperty intelligentObjectProperty, boolean b, Object o,
                                  IntelligentObjectProperty.ValueVersion valueVersion) {
        Object result = this.propertyObject.PropertyUpdated(intelligentObjectProperty, b, o);
        for (IntelligentObjectProperty objectProperty : this.values) {
            if (objectProperty != intelligentObjectProperty) {
                objectProperty.NotifySiblingPropertyUpdated(intelligentObjectProperty, valueVersion);
            }
        }

        EventHandler.fire(this.propertyChangedEventHandler, this,
                new PropertyChangedEventArgs(intelligentObjectProperty.getStringPropertyDefinition().Name()));
        return result;
    }

    @Override
    public Iterator<IProperty> iterator() {
        return null;
    }

    public IntelligentObjectProperty getIntelligentObjectProperty(String name) {
        IntelligentObjectProperty intelligentObjectProperty = this.search((IntelligentObjectProperty match) ->
                Objects.equals(match.Name(), name));
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty;
        }
        return null;
    }

    public void updateStringValue() {
        for (IntelligentObjectProperty intelligentObjectProperty : this.values) {
            String name = intelligentObjectProperty.getDefaultName(this.propertyDefinitions);
            if (!Objects.equals(name, intelligentObjectProperty.StringValue())) {
                intelligentObjectProperty.StringValue(name);
            }
        }
    }

    public IntelligentObjectProperty getIntelligentObjectProperty(String attributeName,
                                                                  IntelligentObjectXml intelligentObjectXml) {
        if (this.propertyObject != null) {
            IntelligentObjectProperty propertyForLoad = this.propertyObject.GetPropertyForLoad(attributeName,
                    intelligentObjectXml);
            if (propertyForLoad != null) {
                return propertyForLoad;
            }
        }
        return this.getIntelligentObjectProperty(attributeName);
    }

    public void insert(int index, StringPropertyDefinition stringPropertyDefinition, Map<Properties,
            IntelligentObjectProperty> originalPropertyInstances) {
        IntelligentObjectProperty intelligentObjectProperty = originalPropertyInstances.get(this);

        if (originalPropertyInstances == null || intelligentObjectProperty != null) {
            intelligentObjectProperty = stringPropertyDefinition.CreateInstance(this);
        }
        super.Insert(index, intelligentObjectProperty);
        IntelligentObjectProperty objectProperty = super.get(index);
        if (objectProperty.getStringPropertyDefinition().RequiredValue() &&
                (Strings.isNullOrEmpty(objectProperty.getStringPropertyDefinition().DefaultString()) ||
                        Objects.equals(objectProperty.getStringPropertyDefinition().DefaultString(), "null"))) {
            objectProperty.processPropertyChange();
            return;
        }
        objectProperty.CompileValue();
    }
}
