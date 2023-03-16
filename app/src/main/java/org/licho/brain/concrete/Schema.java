package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.api.ITableColumn;
import org.licho.brain.api.ITableColumns;
import org.licho.brain.utils.simu.IReferencedObjects;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 */
public class Schema implements IIdentityName, ITableColumns, IListener {

    private final boolean visibility;
    private Table parent;
    private TableDefinition tableDefinition;
    private Targets targets;
    private AbstractGridObjectDefinition abstractGridObjectDefinition;
    private Integer index;


    public Schema(boolean visibility) {
        this.tableDefinition = new TableDefinition();
        this.tableDefinition.Parent(this);
        this.visibility = visibility;
    }

    public Schema() {
        this.tableDefinition = new TableDefinition();
        this.tableDefinition.Parent(this);
        this.visibility = true;
    }

    public GridObjectDefinition BaseDefinition() {
        return this.tableDefinition;
    }

    public PropertyDefinitions Properties() {
        return ((RepeatingPropertyDefinition) this.tableDefinition.getPropertyDefinitions().values.get(0)).propertyDefinitions;
    }

    public Table Parent() {
        return this.parent;
    }

    public void Parent(Table value) {
        this.parent = value;
    }

    public Iterable<StringPropertyDefinition> Columns() {
        return this.Properties().values;
    }

    @Override
    public int Count() {
        return this.Properties().values.size();
    }

    public Targets Targets() {
        return this.targets;
    }


    void PropertyRemoved(StringPropertyDefinition stringPropertyDefinition) {
        // TODO: 2021/11/17
    }

    public void actionTableStatesDefinition(Action<BaseStatePropertyObject> action) {
        for (BaseStatePropertyObject baseStatePropertyObject :
                this.getTableStatesDefinition().getStateDefinitions().StateProperties.values) {
            action.apply(baseStatePropertyObject);
        }
    }

    public AbstractGridObjectDefinition getTableStatesDefinition() {
        if (this.abstractGridObjectDefinition == null) {
            this.abstractGridObjectDefinition = new TableStatesDefinition(this);
        }
        return this.abstractGridObjectDefinition;
    }

    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }

    @Override
    public ITableColumn getByName(String name) {
        return null;
    }


    @Override
    public ITableColumn getByIndex(int index) {
        return null;
    }

    public Table getParent() {
        return parent;
    }

    public void setParent(Table parent) {
        this.parent = parent;
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

    @Override
    public Iterator<ITableColumn> iterator() {
        return null;
    }

    public void addStringPropertyDefinition(StringPropertyDefinition stringPropertyDefinition) {
        if (!this.visibility) {
            return;
        }
        this.Properties().add(stringPropertyDefinition);
        stringPropertyDefinition.IsTableProperty(true);
        if (this.abstractGridObjectDefinition != null && this.abstractGridObjectDefinition.getStateDefinitions().StateProperties.values.size() > 0) {
            // TODO: 2021/11/17
            return;
        }

    }

    public boolean predicateProperty(Predicate<StringPropertyDefinition> predicate) {

        for (StringPropertyDefinition obj : this.Properties().values) {
            if (predicate.test(obj)) {
                return true;
            }
        }
        return false;
    }

    public void processStringPropertyDefinition(Action<StringPropertyDefinition> action) {
        for (StringPropertyDefinition obj : this.Properties().values) {
            action.apply(obj);
        }
    }

    public int getIndex() {
        if (this.index == null) {
            int num = 0;
            for (StringPropertyDefinition stringPropertyDefinition : this.Properties().values) {
                if (stringPropertyDefinition instanceof SequenceDestinationPropertyDefinition) {
                    this.index = num;
                    return num;
                }
                num++;
            }
            this.index = -1;
        }
        return this.index;
    }

    public StringPropertyDefinition getPropertiesStringPropertyDefinition(String name) {
        return this.Properties().findStringPropertyDefinitionInfoByName(name);
    }

}
