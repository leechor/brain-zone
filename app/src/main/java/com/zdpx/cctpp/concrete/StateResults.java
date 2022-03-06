package com.zdpx.cctpp.concrete;

/**
 *
 */
public class StateResults {
    private Table parent;
    private TableStates tableStates;

    public Table Parent() {
        return this.parent;
    }

    public TableStates Instance() {
        return this.tableStates;

    }

    public void reset(TablesStatesWrapper tablesStatesWrapper, ActiveModel.RunModel runModel) {
        // TODO: 2022/2/6 
    }
}
