package com.zdpx.cctpp.concrete;

/**
 *
 */
public class Connector {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
                            LinkDefinition linkDefinition) {
        LinkDefinition definition = new LinkDefinition("Connector", activeModel, null);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Connector.init(definition);
    }

    public static void init(LinkDefinition linkDefinition) {

    }
}
