package org.licho.brain.concrete;

import org.licho.brain.enu.Enum38;
import org.licho.brain.simuApi.IExecutionContext;
import org.licho.brain.simuApi.IPropertyReaders;
import org.licho.brain.simuApi.IRepeatingPropertyReader;
import org.licho.brain.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class ExpressionPropertyRow extends NumericDataPropertyRow implements IRepeatingPropertyReader, IListeners {
    private IExpression expression;

    public ExpressionPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
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
    public int GetCount(IExecutionContext context) {
        return 0;
    }

    @Override
    public IPropertyReaders GetRow(int index, IExecutionContext context) {
        return null;
    }

    public double getDoubleValue() {
        if (super.getReference() == null && this.expression != null) {
            return this.expression.getDoubleValue();
        }
        return Double.NaN;
    }
}
