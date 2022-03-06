package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IRealStateDefinition extends IUnitizedStateDefinition {
    double getInitialValue();

    void setInitialValue(double initialValue);
}
