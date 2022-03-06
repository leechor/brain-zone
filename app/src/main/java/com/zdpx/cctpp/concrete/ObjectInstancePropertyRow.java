package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.utils.simu.IListeners;

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
