package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.SimioUnitType;

/**
 *
 */
public interface IUnitizedStateDefinition extends IStateDefinition {
    SimioUnitType getUnitType();

    void setUnitType(SimioUnitType unitType);

    String getUnitTypeProperty();

    void setUnitTypeProperty(String unitType);
}
