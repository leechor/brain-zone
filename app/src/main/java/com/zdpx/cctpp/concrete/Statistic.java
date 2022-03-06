package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.Browsable;
import com.zdpx.cctpp.annotations.DisplayName;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.utils.simu.Enum60;
import com.zdpx.cctpp.utils.simu.IStatisticsDataSource;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class Statistic implements Comparable<Statistic>, IScenarioResult {
    private IUnit unit;
    private Enum60 enum60 = Enum60.One;
    private double value;
    private UnitType unitType;
    private Identifier identifier;
    private int replicationNumber;

    public Statistic(String scenarioName, int replicationNumber, String dataItem, String statisticType,
                     String statisticCategory, IStatisticsDataSource statisticsDataSource, double value,
                     String contextObjectName, Enum60 param8, UnitType unitType) {
        this.identifier = new Identifier(scenarioName, dataItem, statisticType, statisticCategory,
                statisticsDataSource, contextObjectName);
        this.ReplicationNumber(replicationNumber);
        this.Value(value);
        this.enum60 = param8;
        this.UnitType(unitType);
    }

    Statistic(Identifier identifier, int replicationNumber, double value, UnitType unitType, IUnit unit) {
        this.identifier = identifier;
        this.ReplicationNumber(replicationNumber);
        this.Value(value);
        this.UnitType(unitType);
        this.unit = unit;
    }

    @DisplayName("Replication #")
    public int ReplicationNumber() {
        return this.replicationNumber;
    }

    public void ReplicationNumber(int replicationNumber) {
        this.replicationNumber = replicationNumber;
    }

    public boolean method_2() {
        return this.enum60 == Enum60.One;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String ObjectName() {
        return null;
    }

    @Override
    public String DataSource() {
        return null;
    }

    @Override
    public String StatisticCategory() {
        return null;
    }

    @Override
    public String StatisticType() {
        return null;
    }

    @Override
    public String DataItem() {
        return null;
    }

    @Override
    public double Average() {
        return 0;
    }

    @Override
    public double Minimum() {
        return 0;
    }

    @Override
    public double Maximum() {
        return 0;
    }

    @Override
    public double HalfWidth() {
        return 0;
    }

    @Override
    public double StandardDeviation() {
        return 0;
    }

    @Override
    public int compareTo(Statistic o) {
        return 0;
    }

    @DisplayName("Value")
    public double Value() {
        return this.value;
    }

    public void Value(double value) {
        this.value = value;
    }

    public Identifier Identifier() {
        return this.identifier;
    }

    @Browsable(false)
    public UnitType UnitType() {
        return this.unitType;
    }

    public void UnitType(UnitType value) {
        this.unitType = value;
    }
}
