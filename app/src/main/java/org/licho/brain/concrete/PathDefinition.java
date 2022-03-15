package org.licho.brain.concrete;

/**
 *
 */
public class PathDefinition {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
							LinkDefinition linkDefinition) {
        LinkDefinition definition = new LinkDefinition("Path", activeModel, linkDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        PathDefinition.init(definition);
    }

    public static void init(LinkDefinition linkDefinition) {

    }
}
