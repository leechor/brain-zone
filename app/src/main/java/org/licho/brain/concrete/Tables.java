package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.simuApi.ITable;
import org.licho.brain.simuApi.ITables;

import java.util.Iterator;

/**
 *
 */
public class Tables extends BindingList<Table> implements ITables {
    private final IntelligentObjectDefinition parent;

    public Tables(IntelligentObjectDefinition parent) {
        this.parent = parent;
    }

    private IntelligentObjectDefinition Parent() {
        return this.parent;
    }


    private Table createTable(String name, boolean visibility) {
        Table table = new Table(name, visibility);
        this.add(table);
        return table;
    }

    public Table createSequenceTable() {
        Table table = this.createTable(this.parent.GetUniqueName("SequenceTable"), true);
        table.Schema().addStringPropertyDefinition(new SequenceDestinationPropertyDefinition("Sequence"));
        return table;
    }

    public Table createOutputTable() {
        return this.createTable(this.parent.GetUniqueName("OutputTable"), false);
    }


    @Override
    public ITable getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public ITable getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<ITable> iterator() {
        return null;
    }

    public Table getTableByName(String tableName) {
        return BindingListWrapper.yieldBingdingListItem(this).stream()
                .filter(t -> t.Name().equals(tableName))
                .findFirst()
                .orElse(null);
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Tables", null,
                (XmlReader body) -> Table.readXmlTable(xmlReader, intelligentObjectXml, this.parent) != null);
    }
}
