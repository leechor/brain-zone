package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.api.ITarget;
import org.licho.brain.api.enu.TargetType;

/**
 *
 */
public class Target implements IGridObject, ITarget, IListener {
    private String withinBoundsStatus;
    private String belowBoundsStatus;
    private String aboveBoundsStatus;
    private String noValueStatus;
    private String displayName;
    private Table table;

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
    public String Name() {
        return null;
    }

    @Override
    public TargetType TargetType() {
        return null;
    }

    @Override
    public String ValueExpression() {
        return null;
    }

    @Override
    public String LowerBoundExpression() {
        return null;
    }

    @Override
    public String UpperBoundExpression() {
        return null;
    }

    @Override
    public int MediumRiskThreshold() {
        return 0;
    }

    @Override
    public int HighRiskThreshold() {
        return 0;
    }

    public String getWithinBoundsStatus() {
        if (Strings.isNullOrEmpty(this.withinBoundsStatus)) {
            return EngineResources.Target_WithinBoundsStatus_Default;
        }
        return this.withinBoundsStatus;
    }

    public String getBelowBoundsStatus() {
        if (Strings.isNullOrEmpty(this.belowBoundsStatus)) {
            return EngineResources.Target_BelowBoundsStatus_Default;
        }
        return this.belowBoundsStatus;
    }

    public String getAboveBoundsStatus() {
        if (Strings.isNullOrEmpty(this.aboveBoundsStatus)) {
            return EngineResources.Target_AboveBoundsStatus_Default;
        }
        return this.aboveBoundsStatus;
    }

    public String getNoValueStatus() {
        if (Strings.isNullOrEmpty(this.noValueStatus)) {
            return EngineResources.Target_NoValueStatus_Default;
        }
        return this.noValueStatus;
    }

    public String DisplayName() {
        return this.displayName;
    }

    public void DisplayName(String value){
        			this.displayName = value;
			this.table.method_28(this);

    }
}
