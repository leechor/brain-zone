package org.licho.brain.api;

import org.licho.brain.enu.ConstraintType;
import org.licho.brain.utils.simu.system.Color;
import org.licho.brain.utils.simu.system.DateTime;

/**
 *
 */
public interface IConstraintRecord {
		DateTime StartTime();
		DateTime EndTime();
		String ConstrainedEntityName();
		Color ConstrainedEntityColor();
		String FacilityLocationName();
		String StationName();
		ConstraintType ConstraintType();
		String ConstraintItem();
		Color ConstraintItemColor();
		String ConstraintDescription();

}
