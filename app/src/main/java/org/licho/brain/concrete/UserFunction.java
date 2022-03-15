package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.IListData;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.api.IFunctionTable;
import org.licho.brain.api.IFunctionTableEntries;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public class UserFunction implements INotifyPropertyChanged, IGridObject, IOwner, ISearch, IItemDescriptor,
        IFunctionTable, IFunctionTableEntries, IListData {
    public BindingList<FunctionTableEntry> functionTableEntries;
    private IntelligentObjectDefinition parent;
    private String name;

    public static UserFunction readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                       IntelligentObjectDefinition intelligentObjectDefinition) {
        if (Objects.equals(xmlReader.Name(), "UserFunction")) {
            UserFunction userFunction = intelligentObjectDefinition.getFunctionTables().AddNew();
            userFunction.readXml(xmlReader, intelligentObjectXml);
            return userFunction;
        }
        return null;
    }

    private boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UserFunction", (XmlReader readerattr) ->
        {
            String name = readerattr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && !StringHelper.equalsLocal(name, this.Name())) {
                if (this.Parent() != null && intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two) {
                    this.Name(this.Parent().GetUniqueName(name, false));
                } else {
                    this.Name(name);
                }
            }
            String description = readerattr.GetAttribute("Description");
            if (!Strings.isNullOrEmpty(description)) {
                this.Description(description);
            }
        }, t -> SomeXmlOperator.xmlReaderElementOperator(t, "Entry",
                (XmlReader read) -> {
                    String argument = read.GetAttribute("Argument");
                    String value = read.GetAttribute("Value");
                    if (!Strings.isNullOrEmpty(argument) && !Strings.isNullOrEmpty(value)) {
                        double x = 0.0;
                        double fx = 0.0;
                        x = Double.parseDouble(argument);
                        fx = Double.parseDouble(value);
                        FunctionTableEntry functionTableEntry = new FunctionTableEntry();
                        functionTableEntry.X(x);
                        functionTableEntry.Fx(fx);
                        this.functionTableEntries.add(functionTableEntry);
                    }
                }, null));
    }

    private IntelligentObjectDefinition Parent() {
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
    public String Name() {
        return this.name;
    }

    @Override
    public void Name(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return;
        }
        if (this.parent.getFunctionTables().getUserFunction(value) != null) {
            return;
        }
        String param = this.name;
        this.name = value;
        this.triggerPropertyChangedEvent("Name");
        this.parent.updateUserFunctionName(this, param);
    }

    private void triggerPropertyChangedEvent(String name) {
        // TODO: 2022/1/17
    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public void Description(String description) {

    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public Object getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }
}
