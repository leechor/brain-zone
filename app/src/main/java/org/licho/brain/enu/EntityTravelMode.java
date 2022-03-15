package org.licho.brain.enu;

/**
 * Used for the initial travel mode for ModelEntity, Worker and Vehicle.
 */
public enum EntityTravelMode {
    /**
     * indicates that the entity is required to perform travel movements in free space
     */
    FREE_SPACE_ONLY,
    /**
     * the entity is required to perform travel movements using its currently assigned network
     */
    NETWORK_ONLY,
    /**
     * a preference for the entity to perform travel movements using its currently assigned network,
     * but if no followable network path exists to its next destination then travel in free space is allowed.
     */
    NETWORK_IF_POSSIBLE,
}
