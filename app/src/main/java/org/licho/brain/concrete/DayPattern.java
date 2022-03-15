package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.Browsable;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.IField;
import org.licho.brain.utils.simu.IValidate;
import org.licho.brain.simuApi.IDayPattern;
import org.licho.brain.simuApi.IWorkPeriods;

import java.util.Objects;

/**
 *
 */
public class DayPattern implements INotifyPropertyChanged, IGridObject, IOwner, ISearch, IItemDescriptor, IDayPattern
        , IField, IValidate {

    private String name;
    private IntelligentObjectDefinition parent;
    private WorkPeriods workPeriods;
    private String description;

    public DayPattern(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.parent = intelligentObjectDefinition;
        this.name = this.parent.GetUniqueName("DayPattern");
        this.workPeriods = new WorkPeriods(this.parent, this);
    }

    public static DayPattern readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                     IntelligentObjectDefinition intelligentObjectDefinition) {
        if (Objects.equals(xmlReader.Name(), "DayPattern")) {
            DayPattern dayPattern = new DayPattern(intelligentObjectDefinition);
            dayPattern.readXml(xmlReader, intelligentObjectXml);
            return dayPattern;
        }
        return null;
    }

    void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayPattern", (XmlReader attr) -> {
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Name", this::Name);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
        }, (XmlReader body) -> this.WorkPeriods().readXml(xmlReader, intelligentObjectXml, this.parent, this));

    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
        this.triggerPropertyChangeEvent("Description");
    }

    public void Name(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return;
        }
        String param = this.name;
        this.name = value;
        this.triggerPropertyChangeEvent("Name");
        this.parent.updateObjectName(this, param);
    }

    private void triggerPropertyChangeEvent(String name) {

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
        return this.name;
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
    public boolean FieldIsEditable(String name) {
        return false;
    }

    @Override
    public String Validate(String param0, String param1) {
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
    public IWorkPeriods getWorkPeriods() {
        return null;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return parent == this.parent;
    }

    public WorkPeriods WorkPeriods() {
        return this.workPeriods;
    }

    @Browsable(false)
    public IntelligentObjectDefinition Parent() {
        return this.parent;
    }
}
