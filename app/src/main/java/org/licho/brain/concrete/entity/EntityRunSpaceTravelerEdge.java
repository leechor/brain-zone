package org.licho.brain.concrete.entity;

import org.licho.brain.concrete.enu.TravelerEdgeType;

/**
 *
 */
public class EntityRunSpaceTravelerEdge {
    void init(EntityRunSpace entityRunSpace, TravelerEdgeType travelerEdgeType) {
        this.entityRunSpace = entityRunSpace;
        this.travelerEdgeType = travelerEdgeType;
    }

    public EntityRunSpace getEntityRunSpace() {
        return this.entityRunSpace;
    }

    public TravelerEdgeType getTravelerEdgeType() {
        return this.travelerEdgeType;
    }

    public EntityRunSpaceTravelerEdge Instance() {
        return this.instance;
    }

    public void copy(EntityRunSpaceTravelerEdge entityRunSpaceTravelerEdge) {
        this.instance = entityRunSpaceTravelerEdge;
    }

    void clear() {
        this.entityRunSpace = null;
        this.instance = null;
    }

    private EntityRunSpace entityRunSpace;

    private TravelerEdgeType travelerEdgeType;

    private EntityRunSpaceTravelerEdge instance;
}
