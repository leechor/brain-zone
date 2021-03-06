package org.licho.brain.utils.simu;

import org.licho.brain.concrete.IGridObject;

/**
 *
 */
public interface IGridObjectOperator extends Iterable<IGridObject> {
    IGridObject AddNew();

    void Insert(int param0, IGridObject param1);

    void Remove(IGridObject param0);

    void Swap(int param0, int param1);

    int Count();

    String CollectionName();

    IGridObject get(int index);

    void FinishEdits();

    boolean CanAdd();

    boolean CanRemove();
}
