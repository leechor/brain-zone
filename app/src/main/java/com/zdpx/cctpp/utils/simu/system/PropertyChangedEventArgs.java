package com.zdpx.cctpp.utils.simu.system;

import com.zdpx.cctpp.event.EventArgs;

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
