package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.EntityRunSpace;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

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
