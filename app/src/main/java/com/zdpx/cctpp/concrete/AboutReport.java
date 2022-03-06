package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.simuApi.IScenario;
import com.zdpx.cctpp.utils.simu.Enum60;
import com.zdpx.cctpp.utils.simu.IAboutReport;
import com.zdpx.cctpp.utils.simu.IStatisticsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AboutReport implements IAboutReport {
    private final String name;
    private final int int_0;
    private List<Statistic> statistics = new ArrayList<>();

    public AboutReport(String name, int i) {
        this.name = name;
        this.int_0 = i;
    }

    public List<Statistic> getStatistics() {
        return this.statistics;
    }

    @Override
    public void imethod_0(String param0, String param1, String param2, IStatisticsDataSource param3, double param4,
                          String param5, Enum60 param6, UnitType param7) {

    }

    @Override
    public void imethod_1(IScenario param0) {

    }

}
