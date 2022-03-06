package com.zdpx.cctpp.enu;

/**
 * These choices are found in the <i>Selection Goal</i> property of the Route Step and therefore also in the standard
 * TransferNode since this object uses a Route Step when its <i>Entity Destination Type</i> property is set to
 * 'SelectFromList'. The enumeration is used to decide which node to select from the nodes in the specified Node List.
 */
public enum NodeSelectionGoal {
    SMALLEST_DISTANCE,
    LARGEST_DISTANCE,
    PREFERRED_ORDER,
    CYCLIC,
    RANDOM,
    SMALLEST_VALUE,
    LARGEST_VALUE,
}
