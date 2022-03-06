package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.utils.simu.system.ITypedList;
import com.zdpx.cctpp.simuApi.IWorkPeriodException;
import com.zdpx.cctpp.simuApi.IWorkPeriodExceptions;

import java.beans.PropertyDescriptor;
import java.util.Iterator;

/**
 *
 */
public class WorkPeriodExceptions extends BindingList<WorkPeriodException> implements ITypedList,
        IWorkPeriodExceptions {
    private WorkSchedule workSchedule;
	private IntelligentObjectDefinition parent;

    public WorkPeriodExceptions(IntelligentObjectDefinition parent, WorkSchedule workSchedule) {
        this.parent = parent;
        this.workSchedule = workSchedule;
        super.AllowEdit(true);
        super.AllowNew(true);
        super.AllowRemove(true);
    }

    @Override
    public String GetListName(PropertyDescriptor[] listAccessors) {
        return null;
    }

    @Override
    public PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors) {
        return null;
    }

    @Override
    public IWorkPeriodException create() {
        return null;
    }

    @Override
    public boolean remove(IWorkPeriodException item) {
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
    public IWorkPeriodException getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IWorkPeriodException> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Exceptions", null, (XmlReader body) ->
		{
			WorkPeriodException workPeriodException = WorkPeriodException.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition, gridObject);
			if (workPeriodException != null)
			{
				this.add(workPeriodException);
				return true;
			}
			return false;
		});    }
}
