package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.api.IExperiment;
import org.licho.brain.api.IExperimentConstraint;
import org.licho.brain.utils.simu.IReferencedObjects;

/**
 *
 */
public class ExperimentConstraint implements  INotifyPropertyChanged, IGridObject, IExperimentConstraint, IListener{
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
    public String getName() {
        return null;
    }

    @Override
    public String getExpression() {
        return null;
    }

    @Override
    public void setExpression(String expression) {

    }

    @Override
    public double getLowerBound() {
        return 0;
    }

    @Override
    public void setLowerBound(double lowerBound) {

    }

    @Override
    public double getUpperBound() {
        return 0;
    }

    @Override
    public void setUpperBound(double upperBound) {

    }

    @Override
    public IExperiment getExperiment() {
        return null;
    }
}
