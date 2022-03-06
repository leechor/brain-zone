package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.simuApi.IExecutionContext;
import com.zdpx.cctpp.simuApi.IPropertyReaders;
import com.zdpx.cctpp.simuApi.IRepeatingPropertyReader;
import com.zdpx.cctpp.utils.simu.IListeners;

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
