package com.zdpx.cctpp.concrete;

/**
 *
 */
public interface IPropertyStatus {
    void SetValue(Object param0, Object param1);

    String ValidateValue(Object param0, Object param1);

    void PropertySelected(Object param0);

    boolean PropertyEngaged(Object param0);

    void PropertyButtonClicked(Object param0);

    void SetEnabled(Object param0, boolean param1);

    Object[] GetDisplayedValues(Object param0);

    String GetErrorText(Object param0);

}
