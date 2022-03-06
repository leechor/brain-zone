package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.annotations.DefaultValue;
import com.zdpx.cctpp.annotations.DisplayName;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.utils.simu.IListData;
import com.zdpx.cctpp.utils.simu.system.IBindingList;
import com.zdpx.cctpp.simuApi.IRateTable;
import com.zdpx.cctpp.simuApi.IRateTableInterval;
import com.zdpx.cctpp.simuApi.IRateTableIntervals;
import com.zdpx.cctpp.simuApi.enu.TimeUnit;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public class RateTable implements INotifyPropertyChanged, IGridObject, IOwner, ISearch, IItemDescriptor,
        IRateTable, IRateTableIntervals, Iterable<IRateTableInterval>, IListData {
    public BindingList<RateTableInterval> rateTableIntervals;
    private IntelligentObjectDefinition parent;
    private String name;
    private String description;
    private int intervalUnitSubType;
    private int numberOfIntervals;
    private double intervalSize;

    public static RateTable readXmlRateTable(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                             IntelligentObjectDefinition intelligentObjectDefinition) {
        if (Objects.equals(xmlReader.Name(), "RateTable")) {
            RateTable rateTable = intelligentObjectDefinition.RateTables().AddNew();
            rateTable.readXml(xmlReader, intelligentObjectXml);
            return rateTable;
        }
        return null;
    }

    public void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        int[] index = new int[]{0};
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "RateTable", (XmlReader attr) ->
        {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && !StringHelper.equalsLocal(this.Name(), name)) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.Parent() != null) {
                    this.Name(this.Parent().GetUniqueName(name, false));
                } else {
                    this.Name(name);
                }
            }
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
            String intervalType = attr.GetAttribute("IntervalType");
            if (!Strings.isNullOrEmpty(intervalType)) {
                this.IntervalUnitSubType(AboutUnit.getUnitLevel(UnitType.Time, intervalType));
            }
            String attribute3 = attr.GetAttribute("IntervalSize");
            if (!Strings.isNullOrEmpty(attribute3)) {
                this.IntervalSize(Double.parseDouble(attribute3));
            }
            String attribute4 = attr.GetAttribute("NumberIntervals");
            if (!Strings.isNullOrEmpty(attribute4)) {
                this.NumberOfIntervals(Integer.parseInt(attribute4));
            }
        }, (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Interval", (XmlReader iattr) ->
        {
            String value = iattr.GetAttribute("Value");
            if (!Strings.isNullOrEmpty(value)) {
                double rate =Double.parseDouble(value);
                this.rateTableIntervals.values.get(index[0]).Rate(rate);
            }
            index[0]++;
        }, null));

    }

    private void IntervalSize(double value) {
        if (value <= 0.0) {
            return;
        }
        this.intervalSize = value;
        // TODO: 2022/1/17
//        this.double_0 = AboutUnit.smethod_4(UnitType.Time, this.intervalUnitSubType, null, this.intervalSize);
//        this.double_1 = this.double_0 * (double) this.numberOfIntervals;
//        this.triggerChangePropertyName("IntervalSize");
//        super.ResetBindings();
    }

    @DisplayName("Time Units")
    @DefaultValue((double) 0)
    public int IntervalUnitSubType() {
        return this.intervalUnitSubType;
    }

    public void IntervalUnitSubType(int value) {
        this.intervalUnitSubType = value;
    }

    @DefaultValue((double) 24)
    public int NumberOfIntervals() {
        return this.numberOfIntervals;
    }

    public void NumberOfIntervals(int value) {
        // TODO: 2022/1/17
    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
        this.triggerChangePropertyName("Description");
    }

    public void Name(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return;
        }
        if (this.parent.RateTables().getRateTableByName(value) != null) {
            return;
        }
        String name = this.name;
        this.name = value;
        this.triggerChangePropertyName("Name");
        if (this.parent != null) {
            this.parent.updateObjectName(this, name);
        }
    }

    private void triggerChangePropertyName(String name) {

    }

    public IntelligentObjectDefinition Parent() {
        return this.parent;
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
        return null;
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
    public IBindingList ListData() {
        return null;
    }

    @Override
    public boolean getIsReadOnly() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public double getIntervalSize() {
        return 0;
    }

    @Override
    public void setIntervalSize(double intervalSize) {

    }

    @Override
    public TimeUnit getIntervalSizeUnit() {
        return null;
    }

    @Override
    public void setIntervalSizeUnit(TimeUnit intervalSizeUnit) {

    }

    @Override
    public int getNumberOfIntervals() {
        return 0;
    }

    @Override
    public void setNumberOfIntervals(int numberOfIntervals) {

    }

    @Override
    public IRateTableIntervals getIntervals() {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IRateTableInterval getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IRateTableInterval> iterator() {
        return null;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }
}
