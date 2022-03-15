package org.licho.brain.concrete;

import cn.hutool.core.date.DateTime;
import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.simuApi.IProperties;
import org.licho.brain.simuApi.IProperty;
import org.licho.brain.simuApi.IPropertyReader;
import org.licho.brain.simuApi.IPropertyReaders;
import org.licho.brain.simuApi.IRow;
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
    public AbsPropertyObject AbsPropertyObject;

    public PropertyDefinitions PropertyDefinitions;

    private RepeatStringPropertyRow repeatStringPropertyRow;
    private EventHandler<PropertyChangedEventArgs> propertyChangedEventHandler;


    public Properties(PropertyDefinitions propertyDefinitions, AbsPropertyObject AbsPropertyObject,
                      RepeatStringPropertyRow repeatStringPropertyRow) {
        this.PropertyDefinitions = propertyDefinitions;
        this.AbsPropertyObject = AbsPropertyObject;
        this.repeatStringPropertyRow = repeatStringPropertyRow;
        for (StringPropertyDefinition propertyDefinitionInfo : propertyDefinitions.values) {
            IntelligentObjectProperty objectProperty = propertyDefinitionInfo.CreateInstance(this);
            super.Add(objectProperty);
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
                this.search((IntelligentObjectProperty t) -> Objects.equals(t.getStringPropertyDefinitionInfo().Name(), name));
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
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, "Properties", null, (XmlReader beforeRead) ->
        {
            if (this.AbsPropertyObject != null) {
                this.AbsPropertyObject.LoadOldDefaultValuesForLoadFrom(intelligentObjectXml);
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
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
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

    public AbsPropertyObject getAbsPropertyObject() {
        return AbsPropertyObject;
    }

    public void setAbsPropertyObject(AbsPropertyObject absPropertyObject) {
        this.AbsPropertyObject = absPropertyObject;
    }

    public PropertyDefinitions getPropertyDefinitions() {
        return PropertyDefinitions;
    }

    public void setPropertyDefinitions(PropertyDefinitions propertyDefinitions) {
        this.PropertyDefinitions = propertyDefinitions;
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
        this.AbsPropertyObject.PropertyUpdating(objectProperty);
    }

    public void init() {
        for (IntelligentObjectProperty intelligentObjectProperty : this.values) {
            if (intelligentObjectProperty instanceof RepeatStringPropertyRow) {
                RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow) intelligentObjectProperty;
                for (Properties properties : repeatStringPropertyRow.PropertyDescriptors.values) {
                    properties.init();
                }
            }

            if (this.AbsPropertyObject != null) {
                this.AbsPropertyObject.PropertyUpdating(intelligentObjectProperty);
            }

            intelligentObjectProperty.processPropertyChange();
            if (this.AbsPropertyObject != null) {
                this.AbsPropertyObject.PropertyUpdated(intelligentObjectProperty,
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
        Object result = this.AbsPropertyObject.PropertyUpdated(intelligentObjectProperty, b, o);
        for (IntelligentObjectProperty objectProperty : this.values) {
            if (objectProperty != intelligentObjectProperty) {
                objectProperty.NotifySiblingPropertyUpdated(intelligentObjectProperty, valueVersion);
            }
        }

        EventHandler.fire(this.propertyChangedEventHandler, this,
                new PropertyChangedEventArgs(intelligentObjectProperty.getStringPropertyDefinitionInfo().Name()));
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
            String name = intelligentObjectProperty.getDefaultName(this.PropertyDefinitions);
            if (!Objects.equals(name, intelligentObjectProperty.StringValue())) {
                intelligentObjectProperty.StringValue(name);
            }
        }
    }

    public IntelligentObjectProperty getIntelligentObjectProperty(String attributeName,
                                                                  IntelligentObjectXml intelligentObjectXml) {
        if (this.AbsPropertyObject != null) {
            IntelligentObjectProperty propertyForLoad = this.AbsPropertyObject.GetPropertyForLoad(attributeName,
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
        if (objectProperty.getStringPropertyDefinitionInfo().RequiredValue() &&
                (Strings.isNullOrEmpty(objectProperty.getStringPropertyDefinitionInfo().DefaultString()) ||
                        Objects.equals(objectProperty.getStringPropertyDefinitionInfo().DefaultString(), "null"))) {
            objectProperty.processPropertyChange();
            return;
        }
        objectProperty.CompileValue();
    }
}
