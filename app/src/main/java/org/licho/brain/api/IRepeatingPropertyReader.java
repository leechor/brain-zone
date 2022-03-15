package org.licho.brain.api;

/**
 *
 */
public interface IRepeatingPropertyReader {
    int GetCount(IExecutionContext context);

    IPropertyReaders GetRow(int index, IExecutionContext context);
}
