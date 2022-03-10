package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simuApi.IRows;
import com.zdpx.cctpp.simuApi.ITable;
import com.zdpx.cctpp.utils.simu.IReferencedObjects;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Table implements INotifyPropertyChanged, IGridObject, IAutoComplete, IOwner, ISearch, IItemDescriptor,
        ITable, IListener {

    private Schema schemaColumns;
    private TableData tableData;
    private StateResults stateResults;

    private TargetResults targetResults;
    private IntelligentObjectDefinition parent;

    private List<ForeignKeyPropertyDefinition> foreignKeyPropertyDefinitions;

    private List<ForeignKeyStatePropertyObject> foreignKeyStateProperties;
    private int int_4;

    public Table(String name, boolean visibility) {
        // TODO: 2021/11/17 
    }

    public static Table readXmlTable(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                     IntelligentObjectDefinition intelligentObjectDefinition) {
        Table table = null;
        if (Objects.equals(xmlReader.Name(), "Table")) {
            table = intelligentObjectDefinition.createTable(intelligentObjectDefinition.GetUniqueName("Table"));
            table.readXml(xmlReader, intelligentObjectXml);
        }
        return table;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        // TODO: 2022/1/17
    }

    public TableData Data() {
        return this.tableData;
    }


    void method_23(BaseStatePropertyObject baseStatePropertyObject) {

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
        return this.tableData.Name();
    }

    @Override
    public void Name(String name) {
        // TODO: 2022/1/17 
    }

    @Override
    public IRows getRows() {
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
                                                    IntelligentObjectProperty intelligentObjectProperty,
                                                    Enum38 param3) {

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

    public Schema Schema() {
        return this.schemaColumns;
    }

    public void resetTable(int interactiveVersion, int planResultsVersion, int riskResultsVersion) {
        this.TargetResults().resetTable(interactiveVersion, planResultsVersion, riskResultsVersion);
        this.States().resetTable(interactiveVersion, planResultsVersion, riskResultsVersion);
    }

    public StateResults States() {
        return this.stateResults;
    }

    public IntelligentObjectDefinition Parent() {
        return this.tableData.getTableProperty().Parent();
    }

    public void Parent(IntelligentObjectDefinition value) {
        this.tableData.getTableProperty().Parent(value);
    }

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return this.parent == gridObjectDefinition;

    }

    public void resetRowsBindings() {
        this.Data().resetRowsBindings();

    }

    public void resetTable() {
        this.TargetResults().resetTable();

    }

    TargetResults TargetResults() {
        return this.targetResults;
    }

    public void method_28(Target target) {
        // TODO: 2021/12/17 
    }

    public Object getTableProperty() {
        return this.tableData.getTableProperty();
    }

    public IRows Rows() {
        return this.tableData.Rows();
    }

    public String getDisplayName(IntelligentObjectRunSpace intelligentObjectRunSpace, AbsBaseRunSpace absBaseRunSpace) {
        int num = this.method_0();
        TablesStatesWrapper tablesStatesWrapper = null;
        if (intelligentObjectRunSpace != null && intelligentObjectRunSpace.tablesStatesWrappers != null) {
            tablesStatesWrapper = intelligentObjectRunSpace.tablesStatesWrappers[num];
        }
        int index = -1;
        if (tablesStatesWrapper != null) {
            index = tablesStatesWrapper.getIndex(absBaseRunSpace);
        }
        StringBuilder stringBuilder = new StringBuilder(this.Name());
        if (index != -1) {
            stringBuilder.append(MessageFormat.format("[{0}]", index + 1));
        }
        return stringBuilder.toString();
    }

    private int method_0() {
        return this.int_4;
    }

    public void ResetBindings() {
        this.Data().Rows().ResetBindings();
    }

    public void method_41() {
        // TODO: 2022/2/7 
    }
}
