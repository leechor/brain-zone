package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.IFunction.GetReferenceValues;
import com.zdpx.cctpp.IFunction.ValueStrings;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.utils.simu.IPropertyOperator;

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
