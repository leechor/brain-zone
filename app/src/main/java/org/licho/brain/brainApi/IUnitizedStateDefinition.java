package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedStateDefinition extends IStateDefinition {
    SimioUnitType getUnitType();

    void setUnitType(SimioUnitType unitType);

    String getUnitTypeProperty();

    void setUnitTypeProperty(String unitType);
}
