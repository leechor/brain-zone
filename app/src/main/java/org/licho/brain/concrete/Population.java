package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

import java.util.List;

/**
 *
 */
public class Population {

    private double maximumNumberInSystem;
    private double numberCreated;
    private double numberDestroyed;
    private double currentCapacity;
    private AbsBaseRunSpace agentElementRunSpace;
    private double numberInSystem;
    private double numberInSystemMinimum;
    private double time_31;
    private double double_10;
    private double double_11;
    private double timeInSystemMinimum;
    private double timeInSystemMaximum;
    private double double_2;
    private double double_30;
    private double costPerItemMinimum;
    private double double_16;
    private double double_15;
    private double capacityMinimum;
    private double capacityMaximum;
    private double capacityAllocated;
    private double allocatedMaximum;
    private double capacityAllocatedTotal;
    private double allocatedMinimum;
    private double capacityUtilized;
    private double capacityUtilizedMinimum;
    private double capacityUtilizedMaximum;
    private List<AgentElementRunSpace> agentElementRunSpaces;
    private boolean isSomeStatus;

    public Population(AgentElementRunSpace agentElementRunSpace) {

    }

    public double MaximumNumberInSystem() {
        return this.maximumNumberInSystem;
    }

    public double NumberCreated() {
        return this.numberCreated;
    }

    public double NumberDestroyed() {
        return this.numberDestroyed;
    }

    public double CurrentCapacity() {
        return this.currentCapacity;
    }


    public AbsBaseRunSpace getAgentElementRunSpace() {
        return this.agentElementRunSpace;
    }

    public double getNumberInSystem() {
        return this.numberInSystem;
    }

    public double getNumberInSystem_Minimum() {
        return this.numberInSystemMinimum;
    }

    public double getNumberInSystem_Average() {
        double timeNow = this.agentElementRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.time_31) {
            return this.numberInSystem;
        }
        return this.method_4() / (timeNow - this.time_31);
    }

    private double method_4() {
        return this.double_10 + this.numberInSystem * (this.agentElementRunSpace.getSomeRun().TimeNow() - this.double_11);
    }

    public double getTimeInSystem_Minimum() {
        return this.timeInSystemMinimum;
    }

    public double getTimeInSystem_Maximum() {
        return this.timeInSystemMaximum;
    }

    public double getTimeInSystem_Average() {
        if (this.numberDestroyed > 0.0) {
            return this.double_2 / this.numberDestroyed;
        }
        return Double.NaN;
    }

    public double getCostPerItem_Average() {
        if (this.numberDestroyed > 0.0) {
            return this.double_30 / this.numberDestroyed;
        }
        return Double.NaN;
    }

    public double getCostPerItem_Minimum() {
        return this.costPerItemMinimum;
    }

    public double getCapacity_Average() {
        double timeNow = this.agentElementRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.time_31) {
            return this.currentCapacity;
        }
        return this.method_17() / (timeNow - this.time_31);
    }


    private double method_17() {
        double num = this.agentElementRunSpace.getSomeRun().TimeNow() - this.double_16;
        if (num <= 0.0 || Double.isInfinite(this.double_15)) {
            return this.double_15;
        }
        if (Double.isInfinite(this.currentCapacity)) {
            return this.double_15 = Double.MIN_VALUE;
        }
        return this.double_15 + this.currentCapacity * num;
    }

    public double getCapacityMinimum() {
        return this.capacityMinimum;
    }

    public double getCapacityMaximum() {
        return this.capacityMaximum;
    }

    public double getCapacityAllocated() {
        return this.capacityAllocated;

    }

    public double getAllocatedAverage() {
        double timeNow = this.agentElementRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.time_31) {
            return this.capacityAllocated;
        }
        return this.method_15() / (timeNow - this.time_31);
    }

    private double method_15() {
        return 0;
    }

    public double getAllocatedMaximum() {
        return this.allocatedMaximum;
    }

    public double getCapacityAllocatedTotal() {
        return this.capacityAllocatedTotal;
    }

    public double getAllocated_Minimum() {
        return this.allocatedMinimum;
    }

    public double getCapacityRemaining() {
        return this.currentCapacity - this.capacityAllocated;
    }

    public double getCapacityUtilized() {
        return this.capacityUtilized;
    }

    public double getUtilizedAverage() {
        double timeNow = this.agentElementRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.time_31) {
            return this.capacityUtilized;
        }
        return this.method_20() / (timeNow - this.time_31);
    }

    private double method_20() {
        return 0;
    }

    public double getCapacityUtilizedMinimum() {
        return this.capacityUtilizedMinimum;
    }

    public double getCapacityUtilizedMaximum() {
        return this.capacityUtilizedMaximum;
    }

    public double getCapacityScheduledUtilization() {
        double num = this.getCapacity_Average();
        if (num == 0.0) {
            return 0.0;
        }
        return 100.0 * (this.getUtilizedAverage() / num);
    }

    public List<AgentElementRunSpace> getAgentElementRunSpaces() {
        		return this.agentElementRunSpaces;

    }

    public boolean IsSomeStatus() {
        		return this.isSomeStatus;

    }

    public void method_39(AgentElementRunSpace agentElementRunSpace, TokenRunSpace tokenRunSpace) {
        // TODO: 2022/2/8 
    }
}
