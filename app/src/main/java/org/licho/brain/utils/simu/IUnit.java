package org.licho.brain.utils.simu;

import org.licho.brain.concrete.UnitConversions;
import org.licho.brain.enu.StatisticConfidenceIntervalType;
import org.licho.brain.enu.UnitType;

/**
 *
 */
public interface IUnit {
	int GetUnitsFor(UnitType unitType);

	StatisticConfidenceIntervalType ConfidenceLevel();

	UnitConversions UnitConversions();
}
