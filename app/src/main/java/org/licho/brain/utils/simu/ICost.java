package org.licho.brain.utils.simu;

import org.licho.brain.concrete.Cost;

/**
 *
 */
public interface ICost {
	Cost getCost();

	ICost getParentCostCenter();

	void UpdateCostStateAndRate(double cost, double rate);

	double GetEffectiveCostRate();
}
