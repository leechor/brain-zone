package org.licho.brain.utils.simu;


import org.licho.brain.utils.simu.system.Color;

/**
 *
 */
public interface IPropertyOperator {
    void SetProperty(Object param0);

    Object GetProperty();

    boolean ValidateProperty(Object object, StringBuffer error);

    boolean HasValidator();

    Class<?> PropertyType();

    boolean IsVisible();

    boolean IsEnabled();

    void SetEnabled(boolean param0);

    Color GetColor();

    int ImageIndex();

    String[] DisplayedValuesNeeded();

    String ErrorText();

    IGridObjectOperator GetChildren();
}
