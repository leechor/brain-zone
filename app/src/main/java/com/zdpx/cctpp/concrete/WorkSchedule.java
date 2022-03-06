package com.zdpx.cctpp.concrete;


import com.google.common.base.Strings;
import com.zdpx.cctpp.annotations.Browsable;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.enu.Enum53;
import com.zdpx.cctpp.event.EventHandler;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simuApi.IDayPattern;
import com.zdpx.cctpp.simuApi.IDayPatternReferences;
import com.zdpx.cctpp.simuApi.IWorkDayExceptions;
import com.zdpx.cctpp.simuApi.IWorkSchedule;
import com.zdpx.cctpp.simuApi.containers.SpecialEditor;
import com.zdpx.cctpp.utils.simu.IField;
import com.zdpx.cctpp.utils.simu.IReferencedObjects;
import com.zdpx.cctpp.utils.simu.IValidate;
import com.zdpx.cctpp.utils.simu.system.DateTime;
import com.zdpx.cctpp.utils.simu.system.IDataErrorInfo;
import com.zdpx.cctpp.utils.simu.system.PropertyChangedEventArgs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class WorkSchedule implements IWorkSchedule, INotifyPropertyChanged, IGridObject, IAutoComplete, Iterable,
        IOwner, ISearch, IItemDescriptor, IDataErrorInfo, IListener, IField, IValidate {

    private String uniqueName;

    private DateTime startDate;

    // Token: 0x04001E0E RID: 7694
    private IntelligentObjectDefinition parent;

    // Token: 0x04001E0F RID: 7695
    private WorkSchedules workSchedules;

    // Token: 0x04001E10 RID: 7696
    private final DayPatternRefs dayPatternRefs;

    // Token: 0x04001E11 RID: 7697
    private final WorkDayExceptions workDayExceptions;

    // Token: 0x04001E12 RID: 7698
    private final WorkPeriodExceptions workPeriodExceptions;
    private String description;

    public WorkSchedule(){
        this.dayPatternRefs = null;
        this.workDayExceptions = null;
        this.workPeriodExceptions = null;
    }

    WorkSchedule(IntelligentObjectDefinition parent, WorkSchedules workSchedules) {
        this.parent = parent;
        this.workSchedules = workSchedules;
        this.uniqueName = this.parent.GetUniqueName("WorkSchedule");
        this.dayPatternRefs = new DayPatternRefs(parent);
        this.Days(7);
        this.workDayExceptions = new WorkDayExceptions(parent, this);
        this.workPeriodExceptions = new WorkPeriodExceptions(parent, this);
        this.startDate = RunSetup.calculateStartTime(this.parent.activeModel.getRunSetup().StartTime());
    }

    public static WorkSchedule readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                       IntelligentObjectDefinition intelligentObjectDefinition,
                                       WorkSchedules workSchedules) {
        if (Objects.equals(xmlReader.Name(), "Schedule")) {
            WorkSchedule workSchedule = new WorkSchedule(intelligentObjectDefinition, workSchedules);
            workSchedule.readXml(xmlReader, intelligentObjectXml);
            return workSchedule;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Schedule", (XmlReader attr) ->
                {
                    SomeXmlOperator.readXmlAttributeString(xmlReader, "Name", this::Name);
                    SomeXmlOperator.readXmlIntAttribute(xmlReader, "CycleDays", this::Days);
                    SomeXmlOperator.readDateTimeAttribute(xmlReader, "StartDate", (DateTime d) ->
                            this.StartDate(d.Date()));
                    SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
                },
                (XmlReader body) -> this.DayPatternRefs().readXml(xmlReader, intelligentObjectXml, this.parent) ||
                        this.WorkDayExceptions().readXml(xmlReader, intelligentObjectXml, this.parent, this) ||
                        this.WorkPeriodExceptions().readXml(xmlReader, intelligentObjectXml, this.parent, this) ||
                        this.readXml(xmlReader, intelligentObjectXml, this.parent));

    }

    private boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                            IntelligentObjectDefinition intelligentObjectDefinition) {

        List<WorkPeriod> oldCycles = new ArrayList<>();
        if (SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Cycle", null, (XmlReader body) ->
        {
            WorkPeriod workPeriod = WorkPeriod.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition,
                    this);
            if (workPeriod != null) {
                oldCycles.add(workPeriod);
                return true;
            }
            return false;
        })) {
            if (oldCycles != null) {
                this.method_10(oldCycles);
            }
            return true;
        }
        return false;
    }

    private void method_10(List<WorkPeriod> oldCycles) {
        // TODO: 2022/1/17
    }

    public WorkDayExceptions WorkDayExceptions() {
        return this.workDayExceptions;
    }


    @Browsable(false)
    public DayPatternRefs DayPatternRefs() {
        return this.dayPatternRefs;
    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
        this.triggerPropertyChange("Description");
    }

    @SpecialEditor(Enum53.One)
    public DateTime StartDate() {
        return this.startDate;
    }

    public void StartDate(DateTime value) {
        this.startDate = value.Date();
        this.triggerPropertyChange("StartDate");
    }

    public int Days() {
        return this.dayPatternRefs.size();
    }

    public void Days(int value) {
        while (this.dayPatternRefs.size() < value) {
            DayPatternRef dayPatternRef = this.dayPatternRefs.AddNew();
            EventHandler.subscribe(dayPatternRef.PropertyChanged, this::onPropertyChanged);
        }
        while (this.dayPatternRefs.size() > value) {
            DayPatternRef dayPatternRef = this.dayPatternRefs.get(this.dayPatternRefs.size() - 1);
            EventHandler.unSubscribe(dayPatternRef.PropertyChanged, this::onPropertyChanged);
            this.dayPatternRefs.Remove(this.dayPatternRefs.size() - 1);
        }
        this.triggerPropertyChange("Days");
    }

    private void onPropertyChanged(Object sender, PropertyChangedEventArgs e) {
        if (Objects.equals(e.PropertyName(), "DayPatternName")) {
            DayPatternRef dayPatternRef = (DayPatternRef) sender;
            int index = this.dayPatternRefs.values.indexOf(dayPatternRef);
            String text = this.workSchedules.getPropertyDescriptorNameByIndex(index);
            this.triggerPropertyChange(text);
            String value = dayPatternRef.getNotFoundString();
            if (Strings.isNullOrEmpty(value)) {
                this.parent.PropertyChange(this, text);
                return;
            }
            this.parent.PropertyChangeError(this, text);
        }
    }

    @Override
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return new String[0];
    }

    @Override
    public Object Item() {
        return null;
    }

    @Override
    public String Name() {
        return this.uniqueName;
    }

    public void Name(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return;
        }
        String oldName = this.uniqueName;
        this.uniqueName = value;
        this.triggerPropertyChange("Name");
        this.parent.updateObjectName(this, oldName);
    }

    private void triggerPropertyChange(String name) {

    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return null;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
    }

    @Override
    public void RefreshIfInError() {

    }

    @Override
    public void UpdateForParentObjectPropertyChange(StringPropertyDefinition definitionInfo, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectStateChange(BaseStatePropertyObject baseStatePropertyObject, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectEventChange(EventDefinition eventDefinition, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject absIntelligentPropertyObject,
                                                         Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectListChange(AbsListProperty absListProperty, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectListTupleChange(AbsListProperty absListProperty, Properties properties,
                                                     Enum38 param2) {

    }

    @Override
    public void UpdateForParentObjectTableChange(Table table, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectTableColumnChange(Table table, StringPropertyDefinition stringPropertyDefinition
            , Enum38 param2) {

    }

    @Override
    public void UpdateForParentObjectTableKeyChange(Table table, Properties properties,
                                                    IntelligentObjectProperty intelligentObjectProperty
            , Enum38 param3) {

    }

    @Override
    public void UpdateForParentObjectWorkScheduleChange(WorkSchedule workSchedule, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectDayPatternChange(DayPattern dayPattern, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectChangeoverMatrixChange(ChangeoverMatrix changeoverMatrix, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectLookupTableChange(UserFunction userFunction, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectRateTableChange(RateTable rateTable, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectTokenDefinitionChange(TokenDefinition token, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectTransferPointChange(NodeClassProperty classProperty, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectExpressionFunctionChange(ExpressionFunction expressionFunction, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectInputParameterChange(AbsInputParameter param0, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectLibraryDefinitionChange(IntelligentObjectDefinition intelligentObjectDefinition,
                                                             Enum38 enu) {

    }

    @Override
    public void CollectReferencedObjects(IReferencedObjects param0) {

    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    @Override
    public boolean FieldIsEditable(String name) {
        return false;
    }

    @Override
    public String Validate(String param0, String param1) {
        return null;
    }

    @Override
    public String getErrorByIndex(String propertyName) {
        int num = this.workSchedules.getIndexByPropertyName(propertyName);
        if (num >= 0 && num < this.DayPatternRefs().size()) {
            DayPatternRef dayPatternRef = this.DayPatternRefs().get(num);
            return dayPatternRef.getNotFoundString();
        }
        return null;
    }

    @Override
    public String getError() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public DateTime getStartDate() {
        return null;
    }

    @Override
    public void setStartDate(DateTime startDate) {

    }

    @Override
    public IDayPatternReferences getDayPatternReferences() {
        return null;
    }

    @Override
    public IWorkDayExceptions getWorkDayExceptions() {
        return null;
    }

    @Override
    public Iterator<IDayPattern> iterator() {
        return null;
    }

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return gridObjectDefinition == this.parent;
    }

    public WorkPeriodExceptions WorkPeriodExceptions() {
        return this.workPeriodExceptions;

    }

    public void updatePropertyName(int index) {
        String propertyName = this.workSchedules.getPropertyByIndex(index);
        String value = this.getErrorByIndex(propertyName);
        if (!Strings.isNullOrEmpty(value)) {
            this.parent.PropertyChangeError(this, propertyName);
            return;
        }
        this.parent.PropertyChange(this, propertyName);
    }
}
