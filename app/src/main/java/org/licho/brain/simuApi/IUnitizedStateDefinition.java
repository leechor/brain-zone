package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedStateDefinition extends IStateDefinition {
    SimioUnitType getUnitType();

    void setUnitType(SimioUnitType unitType);

    String getUnitTypeProperty();

    void setUnitTypeProperty(String unitType);
}
