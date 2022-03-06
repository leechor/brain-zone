package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.StatisticsDataSourceInfo;

/**
 *
 */
public class ContainerElementRunSpace extends AbsBaseRunSpace {
    public ContainerElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                    MayApplication application,
                                    com.zdpx.cctpp.concrete.AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
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
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2, IntelligentObjectProperty param3, String param4) {

    }
}
