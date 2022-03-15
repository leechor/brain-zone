package org.licho.brain.brainApi;

/**
 *
 */
public interface IRepeatingProperty extends IProperty {
    IRows getRows();

    IProperties AddRow();

    void RemoveRow(int index);
}
