package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.UnitConversions;
import com.zdpx.cctpp.enu.StatisticConfidenceIntervalType;
import com.zdpx.cctpp.enu.UnitType;

/**
 *
 */
public interface IUnit {
	int GetUnitsFor(UnitType unitType);

	StatisticConfidenceIntervalType ConfidenceLevel();

	UnitConversions UnitConversions();
}
