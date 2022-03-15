package org.licho.brain.concrete;

/**
 *
 */
public class TimePath {

    public static void init(InternalReference internalReference, ActiveModel activeModel,
							LinkDefinition linkDefinition) {
        LinkDefinition definition = new LinkDefinition("TimePath", activeModel, linkDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        TimePath.init(definition);
    }

    public static void init(LinkDefinition linkDefinition) {

    }
}
