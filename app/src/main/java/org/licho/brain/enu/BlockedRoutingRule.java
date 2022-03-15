package org.licho.brain.enu;

/**
 * The choices for defining the action the entity should take if the destinations in the list of candidate
 * nodes are blocked (Blocked Destination Rule). Found in the standard TransferNode when <i>Entity Destination
 * Type</i> is set to 'SelectFromList'.
 */
public enum BlockedRoutingRule {
    PREFER_AVAILABLE,
    SELECT_ANY,
    SELECT_AVAILABLE_ONLY
}
