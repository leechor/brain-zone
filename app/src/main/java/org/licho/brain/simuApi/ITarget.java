package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.TargetType;

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
