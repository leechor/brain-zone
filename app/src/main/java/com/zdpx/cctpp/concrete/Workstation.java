package com.zdpx.cctpp.concrete;

/**
 *
 */
public class Workstation {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
                            FixedDefinition fixedDefinition) {
        FixedDefinition definition = new FixedDefinition("Workstation", activeModel, fixedDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Workstation.init(definition);
    }

    public static void init(FixedDefinition fixedDefinition) {

    }
}
