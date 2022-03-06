package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.simioEnums.ElementScope;

/**
 * Adds behaviors for modeling objects that can be dynamically created and destroyed. are able to move in continuous
 * space or discrete space(on a grid). and which can detect. chase, and intercept other objects, This type of model is
 * particularly useful for agent-based modeling approaches in which a large number(perhaps many thousands) of
 * independently acting agent interact to create the overall behavior of the system.
 */
public class Agent extends IntelligentObject {

    private ExpressionPropertyRow initialDesiredSpeed;
    private BooleanPropertyRow destroyable;
    private NumericDataPropertyRow initialNumberInSystem;
    private NumericDataPropertyRow maximumNumberInSystem;

    public Agent(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.initialDesiredSpeed = (ExpressionPropertyRow) this.properties.values.get(index++);
        this.initialNumberInSystem = (NumericDataPropertyRow) this.properties.values.get(index++);
        this.maximumNumberInSystem = (NumericDataPropertyRow) this.properties.values.get(index++);
        this.destroyable = (BooleanPropertyRow) this.properties.values.get(index++);
        return index;
    }

    @Override
    public void InitializeFacilityLocation(double x, double y, double z) {
        Location location = new Location(x, y, z);
        Direction direction = location.sub(this.location);
        this.location = location;
        if (super.Parent() != null) {
            super.Parent().setLocationDirection(this, this.location, direction);
        }
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        AgentElementRunSpace agentElementRunSpace = new AgentElementRunSpace(sourceIntelligentObjectRunSpace,
                application, this);
        agentElementRunSpace.setPopulation(new Population(agentElementRunSpace));
        return agentElementRunSpace;
    }

    public ExpressionPropertyRow getInitialDesiredSpeed() {
        return initialDesiredSpeed;
    }

    public BooleanPropertyRow getDestroyable() {
        return destroyable;
    }

    public NumericDataPropertyRow getInitialNumberInSystem() {
        return initialNumberInSystem;
    }

    public NumericDataPropertyRow getMaximumNumberInSystem() {
        return maximumNumberInSystem;
    }
}
