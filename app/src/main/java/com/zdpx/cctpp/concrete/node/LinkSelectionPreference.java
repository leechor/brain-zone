package com.zdpx.cctpp.concrete.node;

/**
 * Used by an entity to select an outbound link from this location to the next.
 * This enumeration is found in the {@link BasicNode#outboundTravelMode Outbound Link Preference property} of
 * the standard TransferNode.
 */
public enum LinkSelectionPreference {
    Default,
    Any,
    Available,
    Specific
}
