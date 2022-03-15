package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.property.AbsBaseRunSpace;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RegulatorElementRunSpace extends AbsBaseRunSpace {
    private final List<OutputFlowReceiver> outputFlowReceivers = new ArrayList<>();

    public RegulatorElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                    MayApplication application, AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public int getOutputFlowReceiversNumberItems() {
        return this.outputFlowReceivers.size();
    }

    public EntityRunSpace getEntityRunSpaceByIndex(int index) {
        return this.outputFlowReceivers.get(index).getEntityRunSpace();
    }

    public class OutputFlowReceiver {
        private EntityRunSpace entityRunSpace;

        public EntityRunSpace getEntityRunSpace() {
            return this.entityRunSpace;
        }
    }
}
