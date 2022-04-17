package org.licho.brain.concrete.node;

/**
 * Used by an entity to select an outbound link from this location to the next.
 * This enumeration is found in the BasicNode#outboundTravelMode Outbound Link Preference property of
 * the standard TransferNode.
 */
public enum LinkSelectionPreference {
    Default,
    Any,
    Available,
    Specific
}
