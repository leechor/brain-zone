package org.licho.brain.api;

import org.licho.brain.api.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedStateDefinition extends IStateDefinition {
    SimioUnitType getUnitType();

    void setUnitType(SimioUnitType unitType);

    String getUnitTypeProperty();

    void setUnitTypeProperty(String unitType);
}
