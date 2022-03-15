package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.brainApi.IExecutionContext;
import org.licho.brain.brainApi.extensions.IElement;
import org.licho.brain.brainApi.extensions.IElementProperty;
import org.licho.brain.utils.simu.IListeners;

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
