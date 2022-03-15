package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.utils.simu.IListeners;

import java.util.List;

/**
 *
 */
public class ObjectInstancePropertyRow extends IntelligentObjectProperty implements IListeners {
    private IExpression expression;

    public ObjectInstancePropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
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
    public String getName() {
        return null;
    }

    public IntelligentObjectRunSpace getExpressionResult(IRunSpace runSpace,
                                                         IntelligentObjectRunSpace intelligentObjectRunSpace,
                                                         int index, AbsBaseRunSpace absBaseRunSpace) {
        return (IntelligentObjectRunSpace) super.getExpressionResult(runSpace, intelligentObjectRunSpace,
                absBaseRunSpace, null,
                intelligentObjectRunSpace != null && intelligentObjectRunSpace.getMayApplication().DisableRandomness()
                , index).getAbsBaseRunSpace();
    }
}
