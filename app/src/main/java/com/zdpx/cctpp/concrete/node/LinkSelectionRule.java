package com.zdpx.cctpp.concrete.node;

/**
 * Used by an entity to select an outbound link from this location to its next destination. This enumerator is found in
 * the <i>Outbound Link Rule</i> property of the standard TransferNode. Shortest Path is only used when the entity
 * already has a destination set. It will determine the shortest path between the current location and the destination
 * of the entity. If the entity does not have a destination set (Entity.DestinationNode = Nothing), then ByLinkWeight
 * will be used for routing. ByLinkWeight to look at the <i>Selection Weight</i> properties of the outbound links to
 * determine the routing for the entity.
 */
public enum LinkSelectionRule {
    BY_LINK_WEIGHT,
    SHORTEST_PATH,
}