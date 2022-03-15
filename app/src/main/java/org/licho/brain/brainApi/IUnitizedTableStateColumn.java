package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedTableStateColumn extends ITableStateColumn {
    SimioUnitType getUnitType();

    String getUnitTypeProperty();

    void setUnitType(SimioUnitType unitType);
}
