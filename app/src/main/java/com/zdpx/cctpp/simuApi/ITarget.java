package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.TargetType;

/**
 *
 */
public interface ITarget {
    String Name();

    TargetType TargetType();

    String ValueExpression();

    String LowerBoundExpression();

    String UpperBoundExpression();

    int MediumRiskThreshold();

    int HighRiskThreshold();
}
