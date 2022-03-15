package org.licho.brain.concrete;

import java.util.List;

/**
 *
 */
public class ResourceLogic {
    private double currentCapacity;
    private double initialCapacity;
    private double capacityPrevious;
    private double capacityNext;
    private IntelligentObjectRunSpace intelligentObjectRunSpace;
    private double capacityMinimum;
    private double capacityMaximum;
    private double capacityAllocated;
    private double double_0;
    private double capacityAllocatedMinimum;
    private double capacityAllocatedMaximum;
    private double capacityAllocatedTotal;
    private double capacityUtilized;
    private double capacityUtilizedMinimum;
    private double capacityUtilizedMaximum;
    private double capacityTimeOfLastChange;
    private double capacityTimeOfNextChange;
    private List<ResourceAllocate> resourceAllocates;
    private double double_19;
    private double double_25;
    private double double_26;
    private double double_22;
    private double double_24;
    private double double_21;
    private double double_23;
    private double double_20;

    public ResourceLogic(IntelligentObjectRunSpace intelligentObjectRunSpace) {

    }

    public double CurrentCapacity() {
        return this.currentCapacity;
    }

    public double InitialCapacity() {
        return this.initialCapacity;
    }

    public double CapacityPrevious() {
        return this.capacityPrevious;
    }

    public double CapacityNext() {
        return this.capacityNext;
    }

    public double CapacityAverage() {
        double timeNow = this.intelligentObjectRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.double_0) {
            return this.currentCapacity;
        }
        return this.method_23() / (timeNow - this.double_0);
    }

    private double method_23() {
        return 0;
    }

    public double CapacityMinimum() {
        return this.capacityMinimum;
    }

    public double CapacityMaximum() {
        return this.capacityMaximum;
    }

    public double CapacityAllocated() {
        return this.capacityAllocated;

    }

    public double CapacityAllocatedAverage() {
        double timeNow = this.intelligentObjectRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.double_0) {
            return this.capacityAllocated;
        }
        return this.ameqrdOkJrY() / (timeNow - this.double_0);
    }

    private double ameqrdOkJrY() {
        return 0;
    }

    public double CapacityAllocatedMinimum() {
        return this.capacityAllocatedMinimum;
    }

    public double CapacityAllocatedMaximum() {
        return this.capacityAllocatedMaximum;
    }

    public double CapacityAllocatedTotal() {
        return this.capacityAllocatedTotal;

    }

    public double CapacityRemaining() {
        return this.currentCapacity - this.capacityAllocated;
    }

    public double CapacityUtilized() {
        return this.capacityUtilized;
    }

    public double CapacityUtilizedAverage() {
        double timeNow = this.intelligentObjectRunSpace.getSomeRun().TimeNow();
        if (timeNow == this.double_0) {
            return this.capacityUtilized;
        }
        return this.method_26() / (timeNow - this.double_0);
    }

    private double method_26() {
        return this.double_19 + this.capacityUtilized * (this.intelligentObjectRunSpace.getSomeRun().TimeNow() - this.double_20);
    }

    public double CapacityUtilizedMinimum() {
        return this.capacityUtilizedMinimum;
    }

    public double CapacityUtilizedMaximum() {
        return this.capacityUtilizedMaximum;
    }

    public double CapacityScheduledUtilization() {
        double num = this.CapacityAverage();
        if (num == 0.0) {
            return 0.0;
        }
        return 100.0 * (this.CapacityUtilizedAverage() / num);
    }


    public double CapacityIdleCost() {
        return this.double_25 + (this.intelligentObjectRunSpace.getSomeRun().TimeNow() - this.double_22) * this.double_21;
    }

    public double CapacityUsageCostCharged() {
        return this.double_26 + (this.intelligentObjectRunSpace.getSomeRun().TimeNow() - this.double_24) * this.double_23;
    }

    public double CapacityTimeOfLastChange() {
        return this.capacityTimeOfLastChange;
    }

    public double CapacityTimeOfNextChange() {
        return this.capacityTimeOfNextChange;
    }

    public double CapacityTimeSinceLastChange() {
        return this.intelligentObjectRunSpace.getSomeRun().TimeNow() - this.capacityTimeOfLastChange;
    }

    public double CapacityTimeUntilNextChange() {
        return this.capacityTimeOfNextChange - this.intelligentObjectRunSpace.getSomeRun().TimeNow();
    }

    public List<ResourceAllocate> ResourceOwnersNumberItems() {
        return this.resourceAllocates;
    }

    public int ResourceOwnersIndexOfItem(IntelligentObjectRunSpace owner) {
        int result = -1;
        int num = 0;
        for (ResourceAllocate resourceAllocate : this.resourceAllocates)
        {
            if (resourceAllocate.getResourceOwnersFirstItem() == owner) {
                result = num;
                break;
            }
            num++;
        }
        return result;
    }
}
