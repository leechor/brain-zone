package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IFunction.ErrorString;
import org.licho.brain.IFunction.GetColor;
import org.licho.brain.IFunction.GetGridObjectOperator;
import org.licho.brain.IFunction.PredicateNoArgs;
import org.licho.brain.IFunction.Validator;
import org.licho.brain.IFunction.ValueStrings;
import org.licho.brain.utils.simu.IGridObjectOperator;
import org.licho.brain.utils.simu.IPropertyOperator;
import org.licho.brain.utils.simu.system.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

/**
 *
 */
public class PropertyOperator_0<T> implements IPropertyOperator {
    private final Supplier<T> getter;
    private final Action<T> setter;
    private final GetColor getColor;
    private final GetGridObjectOperator getGetGridObjectOperator;
    private final ErrorString errorString;
    private final PredicateNoArgs isEnabledFun;
    private final PredicateNoArgs isVisibleFun;
    private final ValueStrings valueStrings;
    private final Validator<T> validator;
    private final Class<T> t;

    public PropertyOperator_0(Class<T> t, Supplier<T> getter, Action<T> setter) {
        this(t, getter, setter, null, null, null, null, null);
    }

    public PropertyOperator_0(Class<T> t, Supplier<T> getter, Action<T> setter, GetGridObjectOperator param2) {
        this(t, getter, setter, null, null, null, null, null, param2, null);
    }

    public PropertyOperator_0(Class<T> t, Supplier<T> getter, Action<T> setter, Validator<T> validator) {
        this(t, getter, setter, validator, null, null, null, null);
    }

    public PropertyOperator_0(Class<T> t, Supplier<T> getter, Action<T> setter, ValueStrings param2) {
        this(t, getter, setter, null, param2, null, null, null);
    }

    public PropertyOperator_0(Class<T> t, Supplier<T> getter, Action<T> setter, Validator<T> validator,
                              ValueStrings valueStrings
            , ErrorString errorString, PredicateNoArgs isVisibleFun, PredicateNoArgs isEnabledFun) {
        this(t, getter, setter, validator, valueStrings, errorString, isVisibleFun, isEnabledFun, null, null);
    }

    public PropertyOperator_0(Class<T> t, Supplier<T> getter,
                              Action<T> setter,
                              Validator<T> validator,
                              ValueStrings valueStrings,
                              ErrorString errorString,
                              PredicateNoArgs isVisibleFun,
                              PredicateNoArgs isEnabledFun,
                              GetGridObjectOperator getGridObjectOperator,
                              GetColor getColor) {
        this.t = t;
        this.getter = getter;
        this.setter = setter;
        this.validator = validator;
        this.valueStrings = valueStrings;
        this.isVisibleFun = isVisibleFun;
        this.isEnabledFun = isEnabledFun;
        this.errorString = errorString;
        this.getGetGridObjectOperator = getGridObjectOperator;
        this.getColor = getColor;
    }


    @Override
    public Object GetProperty() {
        return this.GetPropertyValue();
    }

    protected T GetPropertyValue() {
        if (this.getter != null) {
            return this.getter.get();
        }

        try {
            return t.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void SetProperty(Object o) {
        this.SetPropertyValue((T) o);
    }

    protected void SetPropertyValue(T value) {
        if (this.setter != null) {
            this.setter.apply(value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean ValidateProperty(Object object, StringBuffer error) {
        return this.ValidatePropertyValue((T) object, error);
    }

    protected boolean ValidatePropertyValue(T value, StringBuffer error) {
        return this.validator == null || this.validator.apply(value, error);
    }

    @Override
    public boolean HasValidator() {
        return false;
    }

    @Override
    public Class<T> PropertyType() {
        return t;

    }

    @Override
    public boolean IsVisible() {
        return this.isVisibleFun == null || this.isVisibleFun.test();
    }

    @Override
    public boolean IsEnabled() {
        return this.isEnabledFun == null || this.isEnabledFun.test();
    }

    @Override
    public void SetEnabled(boolean value) {
        this.SetEnabledValue(value);
    }

    private void SetEnabledValue(boolean value) {
    }

    @Override
    public Color GetColor() {
        if (this.getColor == null) {
            return Color.Empty();
        }
        return this.getColor.apply();
    }

    @Override
    public int ImageIndex() {
        return -1;
    }

    @Override
    public String[] DisplayedValuesNeeded() {
        if (this.valueStrings == null) {
            return null;
        }
        return this.valueStrings.apply();
    }

    @Override
    public String ErrorText() {
        if (this.errorString == null) {
            return null;
        }
        return this.errorString.apply();
    }

    @Override
    public IGridObjectOperator GetChildren() {
        if (this.getGetGridObjectOperator != null) {
            return this.getGetGridObjectOperator.apply();
        }
        return null;
    }
}
