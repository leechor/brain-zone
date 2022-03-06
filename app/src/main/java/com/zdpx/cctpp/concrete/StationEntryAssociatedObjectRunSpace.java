package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.enu.ConstraintType;
import com.zdpx.cctpp.utils.simu.IAssociatedObjectRunSpace;

/**
 *
 */
public class StationEntryAssociatedObjectRunSpace implements IAssociatedObjectRunSpace {
    private TokenRunSpace tokenRunSpace;

    private EntityRunSpace associatedObjectRunSpace;

    private RideStation rideStation;

    private StationEntryAssociatedObjectRunSpace stationEntryAssociatedObjectRunSpace;

    private Object constraintRecord;

    void method_0(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace, RideStation rideStation) {
        this.tokenRunSpace = tokenRunSpace;
        this.associatedObjectRunSpace = entityRunSpace;
        this.rideStation = rideStation;
        MayApplication application = this.associatedObjectRunSpace.getMayApplication();
        this.constraintRecord = application.getConstraintRecord(this.associatedObjectRunSpace,
                ConstraintType.StationAvailability, this.rideStation.Name(), this.rideStation.ConstraintDescription());
    }


    public TokenRunSpace getAssociatedObject() {
        return this.tokenRunSpace;
    }

    public EntityRunSpace getEntityRunSpace() {
        return this.associatedObjectRunSpace;
    }


    public StationEntryAssociatedObjectRunSpace getEntry() {
        return this.stationEntryAssociatedObjectRunSpace;
    }

    public void setEntry(StationEntryAssociatedObjectRunSpace stationEntryAssociatedObjectRunSpace) {
        this.stationEntryAssociatedObjectRunSpace = stationEntryAssociatedObjectRunSpace;
    }


    @Override
    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return this.associatedObjectRunSpace;
    }

    void clear() {
        this.tokenRunSpace = null;
        this.associatedObjectRunSpace = null;
        this.rideStation = null;
        this.stationEntryAssociatedObjectRunSpace = null;
        this.constraintRecord = null;
    }



}
