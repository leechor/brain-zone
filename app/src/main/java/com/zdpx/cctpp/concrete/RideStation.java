package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.StatisticsDataSourceInfo;
import com.zdpx.cctpp.utils.simu.ICost;

/**
 *
 */
public class RideStation extends AbsBaseRunSpace implements ICost {
    private boolean parked;
    private double numberEntered;
    private double numberExited;
    private double initialCapacity;
    private double capacityPrevious;
    private double double_5;
    private double double_4;
    private double double_6;
    private double capacityMinimum;
    private double capacityMaximum;
    private RideStation associatedStation;
    private RideStation _rideStation0;
    private EntityRunSpace entityRunSpace;

    public RideStation(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject, MayApplication application,
                       AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }


    public boolean isParked() {
        return this.parked;
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
    public Cost getCost() {
        return null;
    }

    @Override
    public ICost getParentCostCenter() {
        return null;
    }

    @Override
    public void UpdateCostStateAndRate(double cost, double rate) {

    }

    @Override
    public double GetEffectiveCostRate() {
        return 0;
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

    public QueueGridItemTrace getQueueGridItemTrace() {
        return (QueueGridItemTrace) this.absBaseTraces[1];

    }

    public BaseQueueGridItemTrace<StationEntryAssociatedObjectRunSpace> EntryQueue() {
        return (BaseQueueGridItemTrace<StationEntryAssociatedObjectRunSpace>) this.absBaseTraces[2];
    }

    public Cost Cost() {
        return (Cost) this.absBaseTraces[4];
    }

    public DoubleTable_1 CurrentCapacity() {
        return (DoubleTable_1) this.absBaseTraces[3];
    }

    public double RideCapacityRemaining() {
        return this.CurrentCapacity().DoubleValue() - (double) this.getQueueGridItemTrace().NumberInQueue();
    }

    public double NumberEntered() {
        return this.numberEntered;
    }

    public double NumberExited() {
        return this.numberExited;
    }

    public double InitialCapacity() {
        return this.initialCapacity;
    }

    public double getCapacityPrevious() {
        return this.capacityPrevious;
    }

    public double getCapacityAverage() {
        double timeNow = super.getSomeRun().TimeNow();
        if (timeNow == this.double_5) {
            return this.CurrentCapacity().DoubleValue();
        }
        double num = this.double_4 + this.CurrentCapacity().DoubleValue() * (timeNow - this.double_6);
        return num / (timeNow - this.double_5);
    }

    public double getCapacityMinimum() {
        return this.capacityMinimum;
    }

    public double getCapacityMaximum() {
        return this.capacityMaximum;
    }

    public RideStation method_8() {
        return this._rideStation0;
    }

    public RideStation getAssociatedStation() {
        return this.associatedStation;
    }


	public EntityRunSpace getEntityRunSpace()
	{
		return this.entityRunSpace;
	}

    public void method_22() {
        // TODO: 2022/2/8 
    }
}
