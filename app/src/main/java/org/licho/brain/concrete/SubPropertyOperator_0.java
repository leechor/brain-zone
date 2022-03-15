package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IFunction.GetReferenceValues;
import org.licho.brain.IFunction.ValueStrings;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.utils.simu.IPropertyOperator;

import java.util.function.Supplier;

/**
 *
 */
public class SubPropertyOperator_0<T> extends PropertyOperator_0<T> implements IPropertyOperator {
    public SubPropertyOperator_0(Class<T> cl, IntelligentObjectProperty param0, Supplier<T> getter, Action<T> setter,
                                 ValueStrings param3, GetReferenceValues param4) {
        super(cl, getter, setter, param3);

    }

    public SubPropertyOperator_0(Class<T> cl, IntelligentObjectProperty param0, Supplier<T> getter, Action<T> setter) {
        super(cl, getter, setter);
    }
}
