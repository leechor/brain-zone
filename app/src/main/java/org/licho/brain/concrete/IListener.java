package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.utils.simu.IReferencedObjects;

/**
 *
 */
public interface IListener {
	void RefreshIfInError();

	void UpdateForParentObjectPropertyChange(StringPropertyDefinition definitionInfo, Enum38 param1);

	void UpdateForParentObjectStateChange(BaseStatePropertyObject baseStatePropertyObject, Enum38 param1);

	void UpdateForParentObjectEventChange(EventDefinition eventDefinition, Enum38 param1);

	void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject absIntelligentPropertyObject, Enum38 param1);

	void UpdateForParentObjectListChange(AbsListProperty absListProperty, Enum38 param1);

	void UpdateForParentObjectListTupleChange(AbsListProperty absListProperty, Properties properties, Enum38 param2);

	void UpdateForParentObjectTableChange(Table table, Enum38 param1);

	void UpdateForParentObjectTableColumnChange(Table table, StringPropertyDefinition stringPropertyDefinition, Enum38 param2);

	void UpdateForParentObjectTableKeyChange(Table table, Properties properties, IntelligentObjectProperty intelligentObjectProperty, Enum38 param3);

	void UpdateForParentObjectWorkScheduleChange(WorkSchedule workSchedule, Enum38 param1);

	void UpdateForParentObjectDayPatternChange(DayPattern dayPattern, Enum38 param1);

	void UpdateForParentObjectChangeoverMatrixChange(ChangeoverMatrix changeoverMatrix, Enum38 param1);

	void UpdateForParentObjectLookupTableChange(UserFunction userFunction, Enum38 param1);

	void UpdateForParentObjectRateTableChange(RateTable rateTable, Enum38 param1);

	void UpdateForParentObjectTokenDefinitionChange(TokenDefinition token, Enum38 param1);

	void UpdateForParentObjectTransferPointChange(NodeClassProperty classProperty, Enum38 param1);

	void UpdateForParentObjectExpressionFunctionChange(ExpressionFunction expressionFunction, Enum38 param1);

	void UpdateForParentObjectInputParameterChange(AbsInputParameter param0, Enum38 param1);

	void UpdateForParentObjectLibraryDefinitionChange(IntelligentObjectDefinition intelligentObjectDefinition, Enum38 enu);

	void CollectReferencedObjects(IReferencedObjects param0);
}
