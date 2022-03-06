package com.zdpx.cctpp.concrete;

/**
 *
 */
public class Separator {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
                            FixedDefinition fixedDefinition) {
        FixedDefinition definition = new FixedDefinition("Separator", activeModel, fixedDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Separator.init(definition);
    }

    public static void init(FixedDefinition fixedDefinition) {

    }

}
