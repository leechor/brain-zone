package com.zdpx.cctpp.utils.simu.system;

import com.zdpx.cctpp.event.EventArgs;

/**
 *
 */
public class AddingNewEventArgs extends EventArgs {
    private Object newObject = null;

    public AddingNewEventArgs() {
        super();
    }

    public AddingNewEventArgs(Object newObject) {
        super();
        this.newObject = newObject;
    }

    public Object NewObject() {

        return newObject;
    }

    public void NewObject(Object value) {
        newObject = value;
    }
}
