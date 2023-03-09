package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.IListData;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.api.INamedList;
import org.licho.brain.api.IRows;
import org.licho.brain.api.enu.ListType;

/**
 *
 */
public abstract class AbsListProperty extends AbsPropertyObject implements IItemDescriptor, INamedList, IListData,
        INumber {
    public AbsListProperty(GridObjectDefinition definition, String name) {
        super(definition, name);
    }

    protected abstract String ListTypeDescription();

    public abstract ListType ListType();

    protected abstract IconIndex AutoCompleteChoiceIconIndex();

    public abstract String XmlTypeName();

    @Override
    public String InstanceName() {
        return super.InstanceName();
    }

    @Override
    public void InstanceName(String value) {
        String instanceName = super.InstanceName();
        super.InstanceName(value);
        super.propertyChanged("Name");
        if (super.Parent() != null) {
            super.Parent().updateInstanceName(this, instanceName);
        }
    }


    public boolean IsANumberedList() {
        return false;
    }

    public boolean RowNumbersAreZeroBased() {
        return false;
    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
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
        return this.ListTypeDescription();
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return this.InstanceName();
    }

    @Override
    public String ObjectType() {
        return this.ListTypeDescription();
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
    public Object get() {
        return null;
    }

    @Override
    public Object minimum() {
        return null;
    }

    @Override
    public Object maximum() {
        return null;
    }

    @Override
    public Object average() {
        return null;
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
    public String Description() {
        return null;
    }

    @Override
    public void Description(String description) {

    }

    @Override
    public IRows Rows() {
        return null;
    }

    public void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                        IntelligentObjectDefinition intelligentObjectDefinition) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, this.XmlTypeName(), (XmlReader attr) -> {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && !StringHelper.equalsLocal(this.InstanceName(), name)) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && intelligentObjectDefinition != null) {
                    this.InstanceName(intelligentObjectDefinition.GetUniqueName(name, false));
                } else {
                    this.InstanceName(name);
                }
            }
            String description = attr.GetAttribute("Description");
            if (!Strings.isNullOrEmpty(description)) {
                this.Description(description);
            }
        }, (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Items", null,
                (XmlReader itemsBody) -> SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Item",
                        (XmlReader itemAttr) -> {
                            String attribute = itemAttr.GetAttribute("Value");
                            if (!Strings.isNullOrEmpty(attribute)) {
                                // TODO: 2022/1/15
                            }
                        }, null)));
    }

    public RepeatStringPropertyRow getRepeatStringProperty() {
        return (RepeatStringPropertyRow) this.properties.values.get(0);
    }

    public String[] method_15() {
        String[] array = new String[this.getRepeatStringProperty().Tuples().Count()];
        for (int i = 0; i < this.getRepeatStringProperty().Tuples().Count(); i++) {
//            array[i] = this.getRepeatStringProperty().Tuples().values.get(i)
            // TODO: 2022/1/17
        }
        return array;
    }
}
