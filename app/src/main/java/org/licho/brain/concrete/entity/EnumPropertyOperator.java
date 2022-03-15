package org.licho.brain.concrete.entity;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IFunction.ValueStrings;
import org.licho.brain.concrete.PropertyOperator_0;

import java.util.function.Supplier;

/**
 *
 */
public class EnumPropertyOperator extends PropertyOperator_0<Enum> {
    private Class<Enum> type;

    public EnumPropertyOperator(Class<Enum> t, Supplier<Enum> getter, Action<Enum> setter, ValueStrings valueStrings,
                                Class<?> type) {
        super(t, getter, setter, valueStrings);
    }

    @Override
    public Class<Enum> PropertyType() {
        return this.type;
    }
}
