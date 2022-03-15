package org.licho.brain.concrete;

/**
 *
 */
public class Error {
    private IGridObject gridObject;

    public boolean setObject(IGridObject gridObject, ActiveModel activeModel) {
        return this.gridObject == gridObject || (activeModel != null && activeModel.changeObject(this.gridObject) == gridObject);
    }
}
