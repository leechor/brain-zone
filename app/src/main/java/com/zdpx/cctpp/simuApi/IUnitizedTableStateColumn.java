package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedTableStateColumn extends ITableStateColumn {
    SimioUnitType getUnitType();

    String getUnitTypeProperty();

    void setUnitType(SimioUnitType unitType);
}
