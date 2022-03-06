package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.utils.simu.IGridObjectOperator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 */
public class PropertyGridObjectOperator implements IGridObjectOperator {
    	private final List<PropertyGridObject> values = new ArrayList<>();
    private IntelligentObjectDefinition intelligentObjectDefinition;

    public PropertyGridObjectOperator(IntelligentObjectDefinition intelligentObjectDefinition, boolean param1,
                                      Supplier<PropertyDefinitions> propertyGroupDefinitionGetter) {

    }

    @Override
    public IGridObject AddNew() {
        return null;
    }

    @Override
    public void Insert(int param0, IGridObject param1) {

    }

    @Override
    public void Remove(IGridObject param0) {

    }

    @Override
    public void Swap(int param0, int param1) {

    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public String CollectionName() {
        return null;
    }

    @Override
    public IGridObject get(int index) {
        return null;
    }

    @Override
    public void FinishEdits() {

    }

    @Override
    public boolean CanAdd() {
        return false;
    }

    @Override
    public boolean CanRemove() {
        return false;
    }

    @Override
    public Iterator<IGridObject> iterator() {
        return null;
    }

    public void updatePropertyStringValue(Properties properties) {
//        for (PropertyGridObject propertyGridObject : this.)
    }

    public IntelligentObjectDefinition getIntelligentObjectDefinition() {
		return this.intelligentObjectDefinition;
    }
    	private void setIntelligentObjectDefinition(IntelligentObjectDefinition value)
	{
		this.intelligentObjectDefinition = value;
	}

    public boolean readXml(XmlReader xmlReader, String name, String nodeOther) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, name, null,
                (XmlReader body) -> PropertyGridObject.readXml(xmlReader, nodeOther, this) != null);
    }
}
