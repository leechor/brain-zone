package com.zdpx.cctpp.concrete.entity;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.IFunction.ValueStrings;
import com.zdpx.cctpp.concrete.PropertyOperator_0;

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
