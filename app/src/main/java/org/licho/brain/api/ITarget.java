package org.licho.brain.api;

import org.licho.brain.api.enu.TargetType;

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
