package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.UnitType;
import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.IState;
import org.licho.brain.api.IStateProperty;
import org.licho.brain.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class StatePropertyRow extends NumericDataPropertyRow implements IStateProperty, IListeners {
    private BaseStatePropertyObject baseState;
    private IExpression expression;

    public StatePropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    public Iterable<IListener> Listeners() {

        if (this.expression != null) {
            return List.of(ExpressionAction.createExpressionActionListener(this.expression,
                    enum38 -> {
                        if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                            super.processPropertyChange();
                            return;
                        }
                        if (enum38 == Enum38.One) {
                            super.setObjectName(this.StringValue());
                            super.PropertyUpdated();
                        }
                    }, null));
        }
        return List.of();

    }

    @Override
    public IState GetState(IExecutionContext context) {
        return null;
    }

    public AbsBaseTrace getReferenceExpressionValue(IRunSpace runSpace,
                                                    IntelligentObjectRunSpace intelligentObjectRunSpace,
                                                    AbsBaseRunSpace absBaseRunSpace) {
        return this.getReferenceExpressionValue(runSpace, intelligentObjectRunSpace, -1, null);
    }

    public AbsBaseTrace getReferenceExpressionValue(IRunSpace runSpace,
                                                    IntelligentObjectRunSpace intelligentObjectRunSpace, int index,
                                                    AbsBaseRunSpace absBaseRunSpace) {
        if (super.getReference() == null && this.getIndex() != -1) {
            AbsBaseRunSpace baseRunSpace = intelligentObjectRunSpace;
            if (this.expression != null) {
                baseRunSpace = this.expression.GetExpressionValue(runSpace, intelligentObjectRunSpace,
                                absBaseRunSpace, UnitType.Unspecified, 0, (intelligentObjectRunSpace != null) ?
                                        intelligentObjectRunSpace.getMayApplication().UnitConversions() : null,
                                intelligentObjectRunSpace != null &&
                                        intelligentObjectRunSpace.getMayApplication().DisableRandomness(), this)
                        .getAbsBaseRunSpace();
            }
            if (baseRunSpace != null && this.getIndex() < baseRunSpace.absBaseTraces.length) {
                return baseRunSpace.absBaseTraces[this.getIndex()];
            }
        } else if (super.getReference() != null) {
            IntelligentObjectRunSpace objectRunSpace = intelligentObjectRunSpace;
            AbsBaseRunSpace outAbsBaseRunSpace = null;
            boolean[] flag = new boolean[]{true};
            StatePropertyRow statePropertyRow = (StatePropertyRow) super.getIntelligentObjectProperty(runSpace,
                    intelligentObjectRunSpace, objectRunSpace, index, outAbsBaseRunSpace, flag);
            if (objectRunSpace == null) {
                objectRunSpace = intelligentObjectRunSpace.getMayApplication().getFixedRunSpace();
            }
            return statePropertyRow.getReferenceExpressionValue(runSpace, objectRunSpace, index, outAbsBaseRunSpace);
        }
        return null;
    }

    private int getIndex() {
        if (this.baseState != null) {
            return this.baseState.index;
        }
        return -1;
    }

}
