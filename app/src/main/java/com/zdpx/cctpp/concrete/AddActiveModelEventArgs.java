package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.event.EventArgs;

/**
 *
 */
public class AddActiveModelEventArgs extends EventArgs {
    private final ActiveModel model;
    private final int index;

    public AddActiveModelEventArgs(ActiveModel activeModel, int index) {
        this.model = activeModel;
        this.index = index;
    }

    public ActiveModel Model() {
        return this.model;
    }

    public int Index() {
        return this.index;
    }
}
