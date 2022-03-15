package org.licho.brain.property;

import lombok.Data;

/**
 * A Property holds a specific data type, It is a static input parameter
 * for the object and does not change during the simulation.
 */
@Data
public class Property {

    private String name;
    private String displayName;
    private String description;
    private boolean requiredValue;
    private boolean visible;
    private Number defaultValue;
    private String value;
    private int unit;
    // TODO: 2021/9/28 还有Switch Property/Switch Condition/Switch Value
}
