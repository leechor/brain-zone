package org.licho.brain.concrete;

import org.licho.brain.event.EventArgs;

/**
 *
 */
public class CancelEventArgs extends EventArgs {
    private boolean cancel;
    public CancelEventArgs(){
        this(false);
    }

    public CancelEventArgs(boolean cancel) {
        this.cancel = cancel;
    }

    public void Cancel(boolean b) {
        this.cancel = b;
    }

    public boolean Cancel() {
        return this.cancel;
    }
}
