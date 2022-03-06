package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.Cost;

/**
 *
 */
public interface ICost {
	Cost getCost();

	ICost getParentCostCenter();

	void UpdateCostStateAndRate(double cost, double rate);

	double GetEffectiveCostRate();
}
