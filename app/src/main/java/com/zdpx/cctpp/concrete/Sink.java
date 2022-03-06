package com.zdpx.cctpp.concrete;

/**
 *
 */
public class Sink {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
							FixedDefinition fixedDefinition) {
        FixedDefinition definition = new FixedDefinition("Sink", activeModel, fixedDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Sink.init(definition);
    }

    public static void init(FixedDefinition fixedDefinition) {
        // TODO: 2022/2/15 
    }
}
