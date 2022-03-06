package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.simuApi.IStateDefinition;
import com.zdpx.cctpp.simuApi.IStateDefinitions;
import com.zdpx.cctpp.simuApi.enu.SimioUnitType;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class StateDefinitions implements IIdentityName, IStateDefinitions {

    public AbsDefinition AbsDefinition;

    public BindingList<BaseStatePropertyObject> StateProperties;

    public StateDefinitions(AbsDefinition absDefinition) {
        this.AbsDefinition = absDefinition;
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
        List<AbsPropertyObject> list = this.AbsDefinition.getAssociatedInstances();
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
        this.AbsDefinition.NotifyStateAdded(this, stateProperty, index);
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

    public AbsDefinition getAbsDefinition() {
        return AbsDefinition;
    }

    public void setAbsDefinition(AbsDefinition absDefinition) {
        this.AbsDefinition = absDefinition;
    }

    @Override
    public Iterator<IStateDefinition> iterator() {
        return this.StateProperties.values.stream().map(IStateDefinition.class::cast).iterator();
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, this.getName(), null,
                (XmlReader body) -> BaseStatePropertyObject.readXml(xmlReader, intelligentObjectXml, this.AbsDefinition) != null);

    }

    private String getName() {
        return "States";
    }

    public void addBaseStateProperty(BaseStatePropertyObject baseStatePropertyObject) {
        this.StateProperties.Add(baseStatePropertyObject);
    }
}
