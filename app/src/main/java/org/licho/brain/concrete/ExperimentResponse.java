package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.api.IExperiment;
import org.licho.brain.api.IExperimentParameter;
import org.licho.brain.api.IExperimentParameters;
import org.licho.brain.api.IExperimentResponse;
import org.licho.brain.api.enu.ResponseObjective;
import org.licho.brain.utils.simu.IReferencedObjects;

import java.util.Iterator;

/**
 *
 */
public class ExperimentResponse implements INotifyPropertyChanged, IGridObject, IExperimentParameters, IExperimentResponse, IListener{
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
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
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
    public ResponseObjective getObjective() {
        return null;
    }

    @Override
    public void setResponseObjective(ResponseObjective responseObjective) {

    }

    @Override
    public boolean getPrimary() {
        return false;
    }

    @Override
    public IExperiment getExperiment() {
        return null;
    }

    @Override
    public IExperimentParameters getParameters() {
        return null;
    }

    @Override
    public IExperimentParameter getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IExperimentParameter getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IExperimentParameter> iterator() {
        return null;
    }
}
