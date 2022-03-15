package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.api.IDayPattern;
import org.licho.brain.api.IDayPatterns;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class DayPatterns extends BindingList<DayPattern> implements IDayPatterns {
    private IntelligentObjectDefinition intelligentObjectDefinition;


    public DayPatterns(IntelligentObjectDefinition intelligentObjectDefinition, List<DayPattern> initialDayPatterns) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
        super.AllowEdit(true);
        super.AllowNew(true);
        super.AllowRemove(true);
        if (initialDayPatterns != null) {
            for (DayPattern dayPattern : initialDayPatterns) {
                super.Add(dayPattern);
            }
        }
    }

    @Override
    public Class<DayPattern> ItemType() {
        return DayPattern.class;
    }

    @Override
    protected Object AddNewCore() {
        DayPattern addNewCore = new DayPattern(this.intelligentObjectDefinition);
        super.Add(addNewCore);
        return addNewCore;
    }

    @Override
    protected void InsertItem(int index, DayPattern value) {
        super.InsertItem(index, value);
        this.intelligentObjectDefinition.insertDayPattern(value, index);
    }

    @Override
    protected void RemoveItem(int index) {
        DayPattern dayPattern = super.get(index);
        super.RemoveItem(index);
        this.intelligentObjectDefinition.RemoveDayPattern(dayPattern);
    }

    @Override
    public IDayPattern create() {
        return null;
    }

    @Override
    public boolean remove(IDayPattern item) {
        return false;
    }

    @Override
    public void remoteAt(int index) {

    }

    @Override
    public void removeRange(int index, int count) {

    }

    @Override
    public void clear() {

    }

    @Override
    public IDayPattern create(String name) {
        return null;
    }

    @Override
    public IDayPattern getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IDayPattern getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IDayPattern> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           IntelligentObjectDefinition intelligentObjectDefinition) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayPatterns", null, (XmlReader body) ->
        {
            DayPattern dayPattern = DayPattern.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition);
            if (dayPattern != null) {
                this.add(dayPattern);
                return true;
            }
            return false;
        });
    }
}
