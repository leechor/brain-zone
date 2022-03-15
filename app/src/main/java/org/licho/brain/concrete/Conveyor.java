package org.licho.brain.concrete;

/**
 *
 */
public class Conveyor {

    public static void init(InternalReference internalReference, ActiveModel activeModel,
							LinkDefinition linkDefinition) {
        LinkDefinition definition = new LinkDefinition("Conveyor", activeModel, linkDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Conveyor.init(definition);
    }

    public static void init(LinkDefinition linkDefinition) {

    }
}
