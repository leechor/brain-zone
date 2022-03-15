package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.brainApi.IWorkPeriod;
import org.licho.brain.brainApi.IWorkPeriods;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;

import java.util.Iterator;

/**
 *
 */
public class WorkPeriods extends BindingList<WorkPeriod> implements IWorkPeriods {
    private IGridObject gridObject;
    private IntelligentObjectDefinition intelligentObjectDefinition;

    public WorkPeriods(IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
        this.gridObject = gridObject;
        super.AllowEdit(true);
        super.AllowNew(true);
        super.AllowRemove(true);
    }

    @Override
    public Class<WorkPeriod> ItemType() {
        return WorkPeriod.class;
    }


    private TimeSpan getTimeSpan() {
        TimeSpan timeSpan = TimeSpan.Zero;
        for (WorkPeriod workPeriod : this.getValues()) {
            if (workPeriod.EndTimeSpan().compareTo(timeSpan) > 0) {
                timeSpan = workPeriod.EndTimeSpan();
            }
        }
        return timeSpan;
    }

    @Override
    protected Object AddNewCore() {
        TimeSpan timeSpan = this.getTimeSpan();
        WorkPeriod workPeriod = new WorkPeriod(this.intelligentObjectDefinition, this.gridObject, timeSpan,
                timeSpan.add(TimeSpan.FromHours(1.0)));
        super.Add(workPeriod);
        return workPeriod;
    }

    @Override
    protected void InsertItem(int index, WorkPeriod workPeriod) {
        super.InsertItem(index, workPeriod);
        workPeriod.setWorkPeriods(this);
        this.intelligentObjectDefinition.addWorkPeriod(workPeriod);
    }

    @Override
    protected void RemoveItem(int index) {
        WorkPeriod workPeriods = super.get(index);
        super.RemoveItem(index);
        this.intelligentObjectDefinition.removeWorkPeriod(workPeriods);
    }

    @Override
    protected void OnListChanged(ListChangedEventArgs listChangedEventArgs) {
        super.OnListChanged(listChangedEventArgs);
        this.intelligentObjectDefinition.resetTable(255);
    }


    @Override
    public IWorkPeriod create() {
        return null;
    }

    @Override
    public boolean remove(IWorkPeriod item) {
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
    public int Count() {
        return 0;
    }

    @Override
    public IWorkPeriod getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IWorkPeriod> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Periods", null, (XmlReader body) ->
        {
            WorkPeriod workPeriod = WorkPeriod.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition,
                    gridObject);
            if (workPeriod != null) {
                this.add(workPeriod);
                return true;
            }
            return false;
        });
    }
}
