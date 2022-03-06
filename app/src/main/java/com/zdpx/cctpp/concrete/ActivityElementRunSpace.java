package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.enu.EntityLocationType;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.StatisticsDataSourceInfo;

/**
 *
 */
public class ActivityElementRunSpace extends AbsBaseRunSpace {
    public ActivityElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                   MayApplication application, AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    @Override
    public IntelligentObjectRunSpace ContextObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace ParentObjectRunSpace() {
        return null;
    }

    @Override
    public AbsIntelligentPropertyObject MyElementInstance() {
        return null;
    }

    @Override
    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String param0) {
        return null;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }
}
