package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.IListData;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.utils.simu.system.IDisposable;

import java.util.Objects;

/**
 *
 */
public class ChangeoverMatrix implements IDisposable, INotifyPropertyChanged, IGridObject, IOwner, ISearch,
        IItemDescriptor, IListener, IListData {
    private IntelligentObjectDefinition parent;
    private String name;
    private String description;
    private AbsListProperty absListProperty;
    private String associatedList;
    private int unitLevel;

    public static ChangeoverMatrix readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                           IntelligentObjectDefinition intelligentObjectDefinition) {
        if (Objects.equals(xmlReader.Name(), "ChangeoverMatrix")) {
            ChangeoverMatrix changeoverMatrix = intelligentObjectDefinition.newChangeoverMatrix();
            changeoverMatrix.readXml(xmlReader, intelligentObjectXml);
            return changeoverMatrix;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        class Inner {
            public String[] data;
        }
        var inner = new Inner();
        SomeXmlOperator.xmlReaderElementAll(xmlReader, "ChangeoverMatrix", (XmlReader attr) -> {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && StringHelper.equalsLocal(this.Name(), name)) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.Parent() != null) {
                    this.Name(this.Parent().GetUniqueName(name, false));
                } else {
                    this.Name(name);
                }
            }

            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
            String list = attr.GetAttribute("List");
            if (!Strings.isNullOrEmpty(list)) {
                this.AssociatedObject(list);
            }

            String timeUnits = attr.GetAttribute("TimeUnits");
            if (!Strings.isNullOrEmpty(timeUnits)) {
                this.unitLevel = AboutUnit.getUnitLevel(UnitType.Time, timeUnits);
            }

        }, (XmlReader beforeBody) -> {
            if (this.absListProperty != null) {
              inner.data = this.absListProperty.method_15();
            }
            return null;
        },  (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "Changeover", (XmlReader coAttr) -> {
            // TODO: 2022/1/17
        }, null), null);
    }


    public String AssociatedList()
	{
			if (this.absListProperty == null)
			{
				return this.associatedList;
			}
			return this.absListProperty.InstanceName();
		}

    public  void AssociatedObject(String value) {
    }


    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
        this.triggerPropertyChange("Description");
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
        return this.name;
    }

    public void Name(String value) {
        if (this.parent.getChangeoverMatrices().getChangeoverMatrix((ChangeoverMatrix changeoverMatrix) ->
                Objects.equals(changeoverMatrix.Name(), value)) == null) {
            String name = this.name;
            this.name = value;
            this.triggerPropertyChange("Name");
            if (this.parent != null) {
                this.parent.updateObjectName(this, name);
            }
        }
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
    public IBindingList ListData() {
        return null;
    }

    @Override
    public boolean getIsReadOnly() {
        return false;
    }

    @Override
    public void Dispose() {

    }

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return this.Parent() == gridObjectDefinition;
    }

    private IntelligentObjectDefinition Parent() {
        return this.parent;
    }
}
