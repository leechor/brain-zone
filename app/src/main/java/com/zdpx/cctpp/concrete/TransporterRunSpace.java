package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;

/**
 *
 */
public class TransporterRunSpace extends EntityRunSpace {

    public int loadedNum;
    private Object pickupNum;
    private Object dropoffNum;

    public TransporterRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                               MayApplication application, IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public RideStation RideStation() {
        return (RideStation) this.AbsBaseRunSpaces[0];
    }

    public Object getPickupNum() {
        return this.pickupNum;
    }

    public Object getDropoffNum() {
        return this.dropoffNum;
    }
}
