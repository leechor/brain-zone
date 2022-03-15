package org.licho.brain.element;

import org.licho.brain.concrete.EventDefinition;
import org.licho.brain.concrete.EventPropertyDefinition;
import org.licho.brain.concrete.ExpressionAction;
import org.licho.brain.concrete.IExpression;
import org.licho.brain.concrete.IListener;
import org.licho.brain.concrete.Properties;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class EventPropertyRow extends IntelligentObjectProperty implements IListeners {

    private EventDefinition eventDefinition;
    private IExpression expression;

    public EventPropertyRow(EventPropertyDefinition exitPointPropertyDefinition, Properties properties) {
        super(exitPointPropertyDefinition, properties);
    }

    public EventDefinition getDefinition() {
        return this.eventDefinition;
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
}
