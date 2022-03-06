package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IRepeatingPropertyReader {
    int GetCount(IExecutionContext context);

    IPropertyReaders GetRow(int index, IExecutionContext context);
}
