package org.licho.brain.concrete;

import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.extensions.IElement;
import org.licho.brain.api.extensions.IElementProperty;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.ElementReferenceType;
import org.licho.brain.enu.Enum38;
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

    public AbsIntelligentPropertyObject getObjectValue() {
        if (this.absIntelligentPropertyObject != null) {
            return this.absIntelligentPropertyObject;
        }
        if (this.expression != null) {
            return this.expression.getPropertyDefinitionModel().AbsIntelligentPropertyObject;
        }
        return null;
    }

    @Override
    public String StringValue() {
        if (this.absIntelligentPropertyObject != null) {
            return this.absIntelligentPropertyObject.InstanceName();
        }
        if (this.expression != null) {
            return this.expression.toString();
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        ElementPropertyDefinition elementPropertyDefinition =
                (ElementPropertyDefinition) super.getStringPropertyDefinition();
        if (elementPropertyDefinition.ReferenceType() == ElementReferenceType.Create) {
            StringBuffer text = new StringBuffer();
            if (this.absIntelligentPropertyObject != null && this.absIntelligentPropertyObject.CanRenameTo(value,
                    text)) {
                super.setObjectName(value);
                this.absIntelligentPropertyObject.InstanceName(value);
            }
        } else {
            super.StringValue(value);
        }
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


}
