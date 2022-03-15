package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;

/**
 *
 */
public class SequenceStep {
    private final EntityRunSpace entityRunSpace;
    private Table table;
    private double[] double_0;

    public SequenceStep(EntityRunSpace entityRunSpace) {
        this.entityRunSpace = entityRunSpace;
    }

    public double getTimeRemaining() {
        if (this.entityRunSpace.tableRowReferences.getTable() == null) {
            return 0.0;
        }
        if (this.table != this.entityRunSpace.tableRowReferences.getTable()) {
            this.method_1();
        }
        if (this.entityRunSpace.getSequenceCurrentJobStepIndex() <= 0) {
            return this.double_0[this.double_0.length - 1];
        }
        return this.double_0[this.double_0.length - 1] - this.double_0[this.entityRunSpace.getSequenceCurrentJobStepIndex() - 1];
    }

    private void method_1() {

    }


}
