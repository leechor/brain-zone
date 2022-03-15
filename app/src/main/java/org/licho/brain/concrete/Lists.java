package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.api.INamedList;
import org.licho.brain.api.INamedLists;

import java.util.Iterator;

/**
 *
 */
public class Lists extends BindingList<AbsListProperty> implements IAutoComplete, INamedLists {

    private final IntelligentObjectDefinition parent;

    public Lists(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.parent = intelligentObjectDefinition;
    }

    private IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    @Override
    public INamedList AddStringList(String Name) {
        return null;
    }

    @Override
    public INamedList AddObjectList(String Name) {
        return null;
    }

    @Override
    public INamedList AddNodeList(String Name) {
        return null;
    }

    @Override
    public INamedList AddTransporterList(String Name) {
        return null;
    }

    @Override
    public INamedList getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public INamedList getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<INamedList> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Lists", null,
                (XmlReader body) -> ListDefinition.readXml(xmlReader, intelligentObjectXml, this.parent) != null);
    }
}
