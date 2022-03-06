package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.Entity;
import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.enu.ConstraintType;
import com.zdpx.cctpp.utils.simu.system.Color;
import com.zdpx.cctpp.utils.simu.system.DateTime;
import com.zdpx.cctpp.simuApi.IConstraintRecord;

/**
 *
 */
public class ConstraintRecord implements IConstraintRecord {
    private final String entityName;
    private final String facilityLocationName;
    private final String stationName;
    private final ConstraintType constraintType;
    private final String constraintItem;
    private final String constraintDescription;
    private final DateTime startTime;
    private final boolean showEntityInGantt;
    private final TableRow[] tableRows;
    private final Color constrainedEntityColor;
    private DateTime endTime = DateTime.MaxValue;
    private ConstraintLog constraintLog;

    public ConstraintRecord(EntityRunSpace entityRunSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                            String name, ConstraintType constraintType, String constraintItem,
                            String constraintDescription, DateTime startTime) {
        this.entityName = entityRunSpace.HierarchicalDisplayName();
        this.facilityLocationName = intelligentObjectRunSpace.HierarchicalDisplayName();
        this.stationName = name;
        this.constraintType = constraintType;
        this.constraintItem = constraintItem;
        this.constraintDescription = constraintDescription;
        this.startTime = startTime;
        this.endTime = DateTime.MaxValue;
        Entity entity = (Entity)entityRunSpace.myElementInstance;
        this.showEntityInGantt =(entity.getGanttVisibilityExpression().getExpressionResult(new TraceRunSpace(intelligentObjectRunSpace,
                entityRunSpace), intelligentObjectRunSpace).toDouble() > 0.0);
        this.tableRows = entityRunSpace.tableRowReferences.getTableRows();
        this.constrainedEntityColor = this.getColor(entityRunSpace, intelligentObjectRunSpace);
    }

    private Color getColor(EntityRunSpace entityRunSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return null;
    }

    @Override
    public DateTime StartTime() {
        return null;
    }

    @Override
    public DateTime EndTime() {
        return null;
    }

    @Override
    public String ConstrainedEntityName() {
        return null;
    }

    @Override
    public Color ConstrainedEntityColor() {
        return null;
    }

    @Override
    public String FacilityLocationName() {
        return null;
    }

    @Override
    public String StationName() {
        return null;
    }

    @Override
    public ConstraintType ConstraintType() {
        return null;
    }

    @Override
    public String ConstraintItem() {
        return null;
    }

    @Override
    public Color ConstraintItemColor() {
        return null;
    }

    @Override
    public String ConstraintDescription() {
        return null;
    }
}
