package org.licho.brain.utils.simu.system;

import org.licho.brain.event.EventArgs;

/**
 *
 */
public class PropertyChangedEventArgs extends EventArgs {
    private final String propertyName;

    public PropertyChangedEventArgs(String propertyName) {
        this.propertyName = propertyName;
    }

    public String PropertyName() {
        return this.propertyName;
    }
}
