package com.zdpx.cctpp.concrete;

/**
 *
 */
public class Source {
    public static void init(InternalReference internalReference, ActiveModel activeModel, FixedDefinition parent) {
        FixedDefinition fixedDefinition = new FixedDefinition("Source", activeModel, parent);
        internalReference.updateSameIntelligentObjectDefinition(fixedDefinition);
        Source.init(fixedDefinition);
    }

    public static void init(FixedDefinition fixedDefinition) {
        // TODO: 2022/2/15 
    }

}
