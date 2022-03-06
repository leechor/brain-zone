package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.enu.ConstraintType;
import com.zdpx.cctpp.utils.simu.system.Color;
import com.zdpx.cctpp.utils.simu.system.DateTime;

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
