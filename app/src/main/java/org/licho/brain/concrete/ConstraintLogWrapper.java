package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.enu.ConstraintType;
import org.licho.brain.utils.simu.system.DateTime;

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
