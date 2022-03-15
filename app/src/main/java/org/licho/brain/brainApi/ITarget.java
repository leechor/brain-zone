package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.TargetType;

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
