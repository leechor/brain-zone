package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.enu.EntityLocationType;
import com.zdpx.cctpp.enu.ObjectCreationReason;

import java.text.MessageFormat;

/**
 *
 */
public class AgentElementRunSpace extends IntelligentObjectRunSpace {
    private Population population;
    private ResourceLogic resourceLogic;
    private int index;
    private ObjectCreationReason creationReason;

    public AgentElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                MayApplication application, IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public Movement Movement() {
        return (Movement) this.absBaseTraces[5];
    }

    public DoubleTable DoubleTable() {
        return (DoubleTable) this.absBaseTraces[6];
    }

    @Override
    public boolean IsAgentPopulationMember() {
        return this.population != null && this.population.getAgentElementRunSpace() != this;
    }

    @Override
    public boolean IsAgentPopulationMemberOnFreeList() {
        return this.IsAgentPopulationMember() && this.index == -1;
    }

    @Override
    public boolean IsAgentPopulationStaticParent() {
        return this.population != null && this.population.getAgentElementRunSpace() == this;
    }

    @Override
    public boolean IsDestroyable() {
        return this.IsAgentPopulationMember() && !super.method_27() && this.population.IsSomeStatus();
    }

    @Override
    public String Name() {
        if (!this.IsAgentPopulationMember()) {
            return super.Name();
        }
        if (this.population.IsSomeStatus()) {
            return MessageFormat.format("{0}.{1}", this.myElementInstance.InstanceName(), super.ID());
        }
        return MessageFormat.format("{0}[{1}]", this.myElementInstance.InstanceName(), this.getIndex() + 1);
    }


    public String getName() {
        return "[" + EntityLocationType.FreeSpace.toString() + "] " + this.CurrentFacilityRunSpace().HierarchicalDisplayName();

    }


    public Population getPopulation() {
        return this.population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    int getIndex() {
        return this.index;
    }

    void setIndex(int index) {
        this.index = index;
    }

    public ResourceLogic getRunSpaceWrapper() {
        return this.resourceLogic;
    }

    public ObjectCreationReason CreationReason() {
        return this.creationReason;
    }
}
