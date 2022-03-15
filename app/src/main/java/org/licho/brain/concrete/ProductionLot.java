package org.licho.brain.concrete;

import java.util.List;

/**
 *
 */
public class ProductionLot {
    private OperationRunSpace operationRunSpace;
    private int currentActivitySequent;
    private List<OperationActivity> operationActivities;
    private int int_1;
    private Object operationQuantity;

    public OperationRunSpace CurrentOperation() {
        return this.operationRunSpace;
    }

    public int getCurrentActivitySequence() {
        return this.currentActivitySequent;
    }

    public OperationActivity getOperationActivity(int index) {
        if (index > 0 && index <= this.int_1) {
            return this.operationActivities.get(index - 1);
        }
        return null;
    }

    public Object OperationQuantity() {
        return this.operationQuantity;
    }
}
