package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.event.EventArgs;

/**
 *
 */
public class CancelEventArgs extends EventArgs {
    private boolean cancel;
    public CancelEventArgs(){
        this(false);
    }

    public CancelEventArgs(boolean b) {
        this.cancel = cancel;
    }

    public void Cancel(boolean b) {
        this.cancel = b;
    }

    public boolean Cancel() {
        return this.cancel;
    }
}
