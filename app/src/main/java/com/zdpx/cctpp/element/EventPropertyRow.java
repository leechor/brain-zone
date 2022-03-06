package com.zdpx.cctpp.element;

import com.zdpx.cctpp.concrete.EventDefinition;
import com.zdpx.cctpp.concrete.EventPropertyDefinition;
import com.zdpx.cctpp.concrete.ExpressionAction;
import com.zdpx.cctpp.concrete.IExpression;
import com.zdpx.cctpp.concrete.IListener;
import com.zdpx.cctpp.concrete.Properties;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.utils.simu.IListeners;

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
