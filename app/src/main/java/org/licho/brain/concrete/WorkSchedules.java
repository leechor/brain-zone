package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.fake.TypeDescriptor;
import org.licho.brain.simuApi.IWorkSchedule;
import org.licho.brain.simuApi.IWorkSchedules;
import org.licho.brain.utils.simu.IValues;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;
import org.licho.brain.utils.simu.system.PropertyDescriptor;

import java.beans.IntrospectionException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class WorkSchedules extends BindingList<WorkSchedule> implements IWorkSchedules, IValues {

    private static final PropertyDescriptorCollection defaultPropertyDescriptorCollection =
            TypeDescriptor.GetProperties(WorkSchedule.class);
    private IntelligentObjectDefinition intelligentObjectDefinition;
    private PropertyDescriptorCollection propertyDescriptorCollection;
    private IGridObject gridObject;

    public WorkSchedules(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
        super.AllowEdit(true);
        super.AllowNew(true);
        super.AllowRemove(true);
    }


    @Override
    public Class<WorkSchedule> ItemType() {
        return WorkSchedule.class;
    }

    @Override
    protected Object AddNewCore() {
        WorkSchedule workSchedule = new WorkSchedule(this.intelligentObjectDefinition, this);
        super.Add(workSchedule);
        return workSchedule;
    }

    @Override
    protected void InsertItem(int index, WorkSchedule value) {
        super.InsertItem(index, value);
        this.onChangeDayOrStartDate();
        this.intelligentObjectDefinition.addWorkSchedule(value, index);
    }

    private void onChangeDayOrStartDate() {
        // TODO: 2022/2/14
    }

    @Override
    protected void RemoveItem(int index) {
        WorkSchedule workSchedule = super.get(index);
        super.RemoveItem(index);
        this.onChangeDayOrStartDate();
        this.intelligentObjectDefinition.removeWorkSchedule(workSchedule);
    }

    @Override
    protected void OnListChanged(ListChangedEventArgs listChangedEventArgs) {
        if (listChangedEventArgs.PropertyDescriptor() != null && (listChangedEventArgs.PropertyDescriptor().Name() ==
                "Days" || listChangedEventArgs.PropertyDescriptor().Name() == "StartDate")) {
            this.onChangeDayOrStartDate();
        }
        super.OnListChanged(listChangedEventArgs);
        this.intelligentObjectDefinition.resetTable(255);
    }

    @Override
    public IWorkSchedule create() {
        return null;
    }

    @Override
    public boolean remove(IWorkSchedule item) {
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
    public IWorkSchedule create(String name) {
        return null;
    }

    @Override
    public IWorkSchedule getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IWorkSchedule getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IWorkSchedule> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           IntelligentObjectDefinition intelligentObjectDefinition) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Schedules", null, (XmlReader body) ->
        {
            WorkSchedule workSchedule = WorkSchedule.readXml(xmlReader, intelligentObjectXml,
                    intelligentObjectDefinition, this);
            if (workSchedule != null) {
                this.add(workSchedule);
                return true;
            }
            return false;
        });
    }

    public String getPropertyDescriptorNameByIndex(int index) {
        PropertyDescriptorCollection itemProperties = this.GetItemProperties(null);
        index += WorkSchedules.defaultPropertyDescriptorCollection.size();
        if (index < 0 || index >= itemProperties.size()) {
            return null;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) itemProperties.get(index);
        return propertyDescriptor.Name();
    }

    public PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] propertyDescriptors) {
        if (this.propertyDescriptorCollection != null) {
            this.propertyDescriptorCollection = new PropertyDescriptorCollection(null);
            for (Object obj : WorkSchedules.defaultPropertyDescriptorCollection) {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) obj;
                this.propertyDescriptorCollection.add(propertyDescriptor);
            }

            int maxNum, minNum;
            if (super.size() <= 0) {
                minNum = maxNum = 7;
            } else {
                List<WorkSchedule> items = super.getValues();
                maxNum = items.stream().max(Comparator.comparing(WorkSchedule::Days)).get().Days();
                minNum = items.stream().min(Comparator.comparing(WorkSchedule::Days)).get().Days();
            }

            for (int i = 0; i < maxNum; i++) {
                var str = MessageFormat.format("Day {0}", i + 1);
                PropertyDescriptor propertyDescriptor = null;
                try {
                    propertyDescriptor = new WorkSchedulePropertyDescriptor(str, i);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                this.propertyDescriptorCollection.add(propertyDescriptor);
            }
            this.method_6(this.propertyDescriptorCollection, maxNum, minNum);
        }
        return null;
    }

    private void method_6(PropertyDescriptorCollection propertyDescriptorCollection, int maxNum, int minNum) {
        // TODO: 2022/2/10 
    }

    public String getPropertyByIndex(int index) {
        PropertyDescriptorCollection itemProperties = this.GetItemProperties(null);
        index += WorkSchedules.defaultPropertyDescriptorCollection.size();
        if (index < 0 || index >= itemProperties.size()) {
            return null;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) itemProperties.get(index);
        return propertyDescriptor.Name();
    }

    public int getIndexByPropertyName(String propertyName) {
        PropertyDescriptorCollection itemProperties = this.GetItemProperties(null);
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) itemProperties.stream()
                .filter(t -> t.getName().equals(propertyName))
                .findFirst().orElse(null);

        WorkSchedulePropertyDescriptor workSchedulePropertyDescriptor = null;
        if (propertyDescriptor instanceof WorkSchedulePropertyDescriptor) {
            return workSchedulePropertyDescriptor.Index();
        }
        return -1;
    }
}
