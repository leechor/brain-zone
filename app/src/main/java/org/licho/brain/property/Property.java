package org.licho.brain.property;

import lombok.Data;

/**
 * A Property holds a specific data type, It is a static input parameter
 * for the object and does not change during the simulation.
 */
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(boolean requiredValue) {
        this.requiredValue = requiredValue;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Number getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Number defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }


}
