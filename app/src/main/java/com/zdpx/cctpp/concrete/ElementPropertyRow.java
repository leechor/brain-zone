package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.simuApi.IExecutionContext;
import com.zdpx.cctpp.simuApi.extensions.IElement;
import com.zdpx.cctpp.simuApi.extensions.IElementProperty;
import com.zdpx.cctpp.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class ElementPropertyRow extends IntelligentObjectProperty implements IElementProperty, IListeners {
    private AbsIntelligentPropertyObject absIntelligentPropertyObject;
    private IExpression expression;

    public ElementPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
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
    public IElement GetElement(IExecutionContext context) {
        return null;
    }

    public AbsIntelligentPropertyObject getObjectValue() {
        if (this.absIntelligentPropertyObject != null) {
            return this.absIntelligentPropertyObject;
        }
        if (this.expression != null) {
            return this.expression.getPropertyDefinitionModel().AbsIntelligentPropertyObject;
        }
        return null;
    }
}
