package org.licho.brain.simuApi;

/**
 *
 */
public interface IRepeatingProperty extends IProperty {
    IRows getRows();

    IProperties AddRow();

    void RemoveRow(int index);
}
