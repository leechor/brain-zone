package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.api.IStateDefinition;
import org.licho.brain.api.IStateDefinitions;
import org.licho.brain.api.enu.SimioUnitType;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class StateDefinitions implements IIdentityName, IStateDefinitions {
    // AbsDefinition
    public AbsDefinition parent;

    public BindingList<BaseStatePropertyObject> StateProperties;

    public StateDefinitions(AbsDefinition absDefinition) {
        this.parent = absDefinition;
        this.StateProperties = new BindingList<>();
    }

    public void addStateProperty(BaseStatePropertyObject stateProperty) {
        stateProperty.StateDefinitions = this;
        this.insertStateProperty(this.StateProperties.values.size(), stateProperty);
    }

    public void insertStateProperty(int index, BaseStatePropertyObject stateProperty) {
        if (stateProperty.StateDefinitions == null) {
            stateProperty.StateDefinitions = this;
        }
        this.StateProperties.Insert(index, stateProperty);
        List<AbsPropertyObject> list = this.parent.getAssociatedInstances();
        for (AbsPropertyObject absPropertyObject : list) {
            AbsIntelligentPropertyObject absIntelligentPropertyObject =
                    (AbsIntelligentPropertyObject) absPropertyObject;
            StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList = absIntelligentPropertyObject.StateIGridItemPropertyObjectList;
            stateIGridItemPropertyObjectList.add(index, stateProperty.CreateInstance(stateIGridItemPropertyObjectList));
        }
        int num = 0;
        for (BaseStatePropertyObject baseStatePropertyObject : this.StateProperties.values) {
            if (baseStatePropertyObject.StateDefinitions == this) {
                baseStatePropertyObject.index = num;
            }
            num++;
        }
        this.parent.NotifyStateAdded(this, stateProperty, index);
    }

    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IStateDefinition getByIndex(int index) {
        return null;
    }

    @Override
    public IStateDefinition addState(String name) {
        return null;
    }

    @Override
    public IStateDefinition addRealState(String name) {
        return null;
    }

    @Override
    public IStateDefinition addRealState(String name, SimioUnitType unitType) {
        return null;
    }

    @Override
    public IStateDefinition addStringState(String name) {
        return null;
    }

    @Override
    public IStateDefinition addDateTimeState(String name) {
        return null;
    }

    @Override
    public IStateDefinition addListState(String name) {
        return null;
    }

    @Override
    public IStateDefinition getByName(String name) {
        return null;
    }

    public AbsDefinition getParent() {
        return parent;
    }

    public void setParent(AbsDefinition parent) {
        this.parent = parent;
    }

    @Override
    public Iterator<IStateDefinition> iterator() {
        return this.StateProperties.values.stream().map(IStateDefinition.class::cast).iterator();
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, this.getName(), null,
                (XmlReader body) -> BaseStatePropertyObject.readXml(xmlReader, intelligentObjectXml, this.parent) != null);

    }

    private String getName() {
        return "States";
    }

    public void addBaseStateProperty(BaseStatePropertyObject baseStatePropertyObject) {
        this.StateProperties.Add(baseStatePropertyObject);
    }
}
