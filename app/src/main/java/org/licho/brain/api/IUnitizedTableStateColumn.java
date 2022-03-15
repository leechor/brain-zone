package org.licho.brain.api;

import org.licho.brain.api.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedTableStateColumn extends ITableStateColumn {
    SimioUnitType getUnitType();

    String getUnitTypeProperty();

    void setUnitType(SimioUnitType unitType);
}
