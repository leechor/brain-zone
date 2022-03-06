package com.zdpx.cctpp.concrete;

/**
 *
 */
public class RoutingTravelers {
    private NodeRunSpace nodeRunSpace;
    private int routingInToEnterAssociatedObject;
    private int travelersNumber;
    private int routingInNumber;

    public RoutingTravelers(NodeRunSpace nodeRunSpace) {
        this.nodeRunSpace = nodeRunSpace;
    }

    public int getRoutingInToEnterAssociatedObject() {
        return this.routingInToEnterAssociatedObject;
    }

    public int getTravelersNumber() {
        return this.travelersNumber;
    }

    public int getRoutingInNumber() {
        return this.routingInNumber;
    }
}
