package org.licho.brain.utils.simu.system;

import org.licho.brain.event.EventArgs;

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
