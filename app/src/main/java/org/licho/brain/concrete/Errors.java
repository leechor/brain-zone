package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IGridObjectOperator;
import org.licho.brain.api.IError;
import org.licho.brain.api.IErrors;

import java.util.Iterator;

/**
 *
 */
public class Errors extends BindingList<Error> implements IErrors {
    private final ActiveModel activeModel;

    public Errors(ActiveModel activeModel) {
        this.activeModel = activeModel;
        super.AllowEdit(false);
    }


    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IError getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IError> iterator() {
        return null;
    }

    public void RemoveErrorByObject(IGridObject t) {
        this.removeErrorByObject(this.activeModel.changeObject(t));
    }

    private void removeErrorByObject(IGridObject gridObject) {
        GridItemProperties gridItemProperties = new GridItemProperties();
        gridObject.GetGridPropertyItemList(gridItemProperties, null);
        for (GridItemProperty gridItemProperty : gridItemProperties) {
            if (gridItemProperty.getPropertyOperator() != null) {
                IGridObjectOperator children = gridItemProperty.getPropertyOperator().GetChildren();
                if (children != null) {
                    for (IGridObject o : children) {
                        this.removeErrorByObject(o);
                    }
                }
            }
        }
        for (int i = super.values.size() - 1; i >= 0; i--) {
            if (super.values.get(i).setObject(gridObject, this.activeModel)) {
                super.values.remove(i);
            }
        }
    }

    public void ResetAllPropertiesError(IGridObject gridObject) {
        this.clear();
        this.updateAllPropertiesError(this.activeModel.changeObject(gridObject));
    }

    private void updateAllPropertiesError(IGridObject changeObject) {
        // TODO: 2022/1/12 
    }

    public void clear() {
        // TODO: 2022/1/12
    }
}
