package org.licho.brain.simuApi;

/**
 *
 */
public interface IRepeatingPropertyReader {
    int GetCount(IExecutionContext context);

    IPropertyReaders GetRow(int index, IExecutionContext context);
}
