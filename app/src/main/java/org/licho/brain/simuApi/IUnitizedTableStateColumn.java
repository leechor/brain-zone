package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedTableStateColumn extends ITableStateColumn {
    SimioUnitType getUnitType();

    String getUnitTypeProperty();

    void setUnitType(SimioUnitType unitType);
}
