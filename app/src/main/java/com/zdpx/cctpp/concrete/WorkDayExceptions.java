package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.utils.simu.IValues;
import com.zdpx.cctpp.utils.simu.system.ITypedList;
import com.zdpx.cctpp.simuApi.IWorkDayException;
import com.zdpx.cctpp.simuApi.IWorkDayExceptions;

import java.beans.PropertyDescriptor;
import java.util.Iterator;

/**
 *
 */
public class WorkDayExceptions extends BindingList<WorkDayException> implements ITypedList, IWorkDayExceptions, IValues {
    private final WorkSchedule workSchedule;
    private final IntelligentObjectDefinition parent;

    public WorkDayExceptions(IntelligentObjectDefinition parent, WorkSchedule workSchedule) {
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
    public IWorkDayException create() {
        return null;
    }

    @Override
    public boolean remove(IWorkDayException item) {
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
    public IWorkDayException getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IWorkDayException> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {
	return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayExceptions", null, (XmlReader body) ->
		{
			WorkDayException workDayException = WorkDayException.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition, gridObject);
			if (workDayException != null)
			{
				this.add(workDayException);
				return true;
			}
			return false;
		});    }
}
