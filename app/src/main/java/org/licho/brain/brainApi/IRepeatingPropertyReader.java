package org.licho.brain.brainApi;

/**
 *
 */
public interface IRepeatingPropertyReader {
    int GetCount(IExecutionContext context);

    IPropertyReaders GetRow(int index, IExecutionContext context);
}
