package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IRepeatingProperty extends IProperty {
    IRows getRows();

    IProperties AddRow();

    void RemoveRow(int index);
}
