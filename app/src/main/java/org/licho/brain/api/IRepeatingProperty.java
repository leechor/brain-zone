package org.licho.brain.api;

/**
 *
 */
public interface IRepeatingProperty extends IProperty {
    IRows getRows();

    IProperties AddRow();

    void RemoveRow(int index);
}
