package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.utils.simu.IReferencedObjects;

/**
 *
 */
public class ExpressionAction {

    public static IListener createExpressionActionListener(IExpression expression, Action<Enum38> updateAction) {
        return new ExpressionAction.ExpressionActionListener(expression, updateAction, null);
    }

    public static IListener createExpressionActionListener(IExpression expression, Action<Enum38> updateAction,
                                                           Runnable action) {
        return new ExpressionAction.ExpressionActionListener(expression, updateAction, action);
    }

    private static class ExpressionActionListener implements IListener {
        private final IExpression expression;
        private Action<Enum38> updateAction;
        private Runnable action;

        public ExpressionActionListener(IExpression expression, Action<Enum38> updateAction, Runnable action) {
            this.expression = expression;
            this.updateAction = updateAction;
            this.action = action;
        }

        @Override
        public void RefreshIfInError() {
            if (this.action != null) {
                this.action.run();
            }
        }

        @Override
        public void UpdateForParentObjectPropertyChange(StringPropertyDefinition stringPropertyDefinition,
                                                        Enum38 enum38) {
            if (this.expression != null && this.expression.equal(stringPropertyDefinition)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectStateChange(BaseStatePropertyObject baseStatePropertyObject, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(baseStatePropertyObject)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectEventChange(EventDefinition eventDefinition, Enum38 enum38) {

        }

        @Override
        public void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject absIntelligentPropertyObject
                , Enum38 enum38) {
            if (this.expression != null && this.expression.equal(absIntelligentPropertyObject)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectListChange(AbsListProperty absListProperty, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(absListProperty)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectListTupleChange(AbsListProperty absListProperty, Properties properties,
                                                         Enum38 enum38) {
            if (this.expression != null && this.expression.equal(absListProperty)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectTableChange(Table table, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(table)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectTableColumnChange(Table table,
                                                           StringPropertyDefinition stringPropertyDefinition,
                                                           Enum38 enum38) {
            if (this.expression != null && this.expression.equal(table)) {
                this.updateAction.apply(enum38);
            }
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
        public void UpdateForParentObjectLookupTableChange(UserFunction userFunction, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(userFunction)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectRateTableChange(RateTable rateTable, Enum38 param1) {

        }

        @Override
        public void UpdateForParentObjectTokenDefinitionChange(TokenDefinition tokenDefinition, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(tokenDefinition)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectTransferPointChange(NodeClassProperty classProperty, Enum38 param1) {

        }

        @Override
        public void UpdateForParentObjectExpressionFunctionChange(ExpressionFunction expressionFunction, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(expressionFunction)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectInputParameterChange(AbsInputParameter absInputParameter, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(absInputParameter)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void UpdateForParentObjectLibraryDefinitionChange(IntelligentObjectDefinition intelligentObjectDefinition, Enum38 enum38) {
            if (this.expression != null && this.expression.equal(intelligentObjectDefinition)) {
                this.updateAction.apply(enum38);
            }
        }

        @Override
        public void CollectReferencedObjects(IReferencedObjects referencedObjects) {
            if (this.expression != null) {
                this.expression.CollectReferencedObjects(referencedObjects);
            }
        }
    }
}
