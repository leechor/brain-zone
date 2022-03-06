package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.enu.ConstraintType;
import com.zdpx.cctpp.utils.simu.system.DateTime;

/**
 *
 */
public class ConstraintLogWrapper {
	private ConstraintLog constraintLog;

    public ConstraintLogWrapper(ConstraintRecord constraintRecord) {
		this.constraintLog = constraintLog;
    }

    public Object getConstraintRecord(EntityRunSpace entityRunSpace, ConstraintType constraintType, String name,
									  String description, DateTime dateTime) {
        if (entityRunSpace != null) {
            RideStation rideStation = entityRunSpace.CurrentStation();
            IntelligentObjectRunSpace intelligentObjectRunSpace = (rideStation != null) ?
					rideStation.ParentObjectRunSpace : entityRunSpace.CurrentParentObjectRunSpace;
            ConstraintRecord constraintRecord = new ConstraintRecord(entityRunSpace, intelligentObjectRunSpace,
					(rideStation != null) ? rideStation.Name() : null, constraintType, name, description, dateTime);
            this.constraintLog.add(constraintRecord);
            return constraintRecord;
        }
        return null;
    }

    public void method_1(Object constraintRecord, DateTime dateTime) {
        // TODO: 2022/1/21 
    }
}
