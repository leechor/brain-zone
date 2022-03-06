package com.zdpx.cctpp.concrete;

/**
 * A <b>Resource</b> is a static capacity Fixed Object that can be seized. It does not move within the Facility window.
 * If you would like a movable Resource, consider seizing capacity of a {@link Vehicle} or {@link Worker} object.
 * <p>
 * A resource's capacity can be fixed or controlled by a work schedule. A resource object may have a capacity of 0 to N
 * and is considered 'OffShift' if capacity=0 or 'OnShift' if capacity > 0.
 * <p>
 * Resources can be grouped together in an object list, so they can be seized as a group or selected from a list of
 * possible resources based on a selection criteria. A seized operation requires a negotiation between the objects
 * involved. An object could refuse the seized request because he/she has other activities to perform.
 * <p></p>
 * A Resource maybe failures.
 */
public class Resource {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
							FixedDefinition fixedDefinition) {
        FixedDefinition definition = new FixedDefinition("Resource", activeModel, fixedDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Resource.init(definition);
    }


    public static void init(FixedDefinition fixedDefinition) {

    }
}
